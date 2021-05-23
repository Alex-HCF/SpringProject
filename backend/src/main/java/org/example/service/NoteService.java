package org.example.service;

import info.debatty.java.stringsimilarity.JaroWinkler;
import org.example.data.dto.SearchDto;
import org.example.data.entity.Category;
import org.example.data.entity.Note;
import org.example.data.entity.NoteStatus;
import org.example.data.mapper.LocationInMapper;
import org.example.exception.EntityNotFound;
import org.example.repository.NoteRepository;
import org.example.utils.LocationUtils;
import org.example.utils.geoTools.DaDataGeoTool;
import org.example.utils.geoTools.GeoData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final DaDataGeoTool geoTool;
    private final LocationUtils locationUtils;
    private final CategoryService categoryService;
    private final LocationInMapper locationInMapper;

    public NoteService(NoteRepository noteRepository, DaDataGeoTool geoTool, LocationUtils locationUtils, CategoryService categoryService, LocationInMapper locationInMapper) {
        this.noteRepository = noteRepository;
        this.geoTool = geoTool;
        this.locationUtils = locationUtils;
        this.categoryService = categoryService;
        this.locationInMapper = locationInMapper;
    }

    public Note addNote(Note note){
        note.setNoteStatus(NoteStatus.OPEN);
        if(!categoryService.existsById(note.getCategory().getId())){
            throw new EntityNotFound(Category.class, note.getCategory().getId());
        }
        return noteRepository.save(note);
    }

    public Note updateNote(Note note){
        if(!noteRepository.existsById(note.getId())){
            throw new EntityNotFound(Note.class, note.getId());
        }
        return noteRepository.save(note);
    }

    public void deleteNote(Long id){
        if(!noteRepository.existsById(id)){
            throw new EntityNotFound(Note.class, id);
        }
        noteRepository.deleteById(id);
    }

    public Note findById(Long id){
        var res = noteRepository.findById(id);
        return res.orElseThrow(() -> new EntityNotFound(Note.class, id));
    }

    //refactoring...
    public List<Note> findBySearchQuery(SearchDto searchDto){
        Double minPrice = searchDto.getMinPrice() == null ? Double.MIN_VALUE : searchDto.getMinPrice();
        Double maxPrice = searchDto.getMaxPrice() == null ? Double.MAX_VALUE : searchDto.getMaxPrice();

        List<Long> categoriesId = categoryService.findCategoriesWithParentId(searchDto.getCategoryId())
                .stream().map(Category::getId)
                .collect(Collectors.toList());
        categoriesId.add(searchDto.getCategoryId());

        GeoData geoData = geoTool.getGeoDataFromAddress(searchDto.getAddress());
        var fiasWithType = locationUtils.
                findTheMostAccurateFias(locationInMapper.geoDataToLocation(geoData));
        var type = fiasWithType.getFiasType();
        var fias = fiasWithType.getFiasId();

        List<Note> res = new ArrayList<>();

        switch (type){
            case HOUSE -> res = noteRepository.findByPriceAndCategoryAndHouse(1., 2., Arrays.asList(1L, 2L), UUID.fromString("b756fe6b-bbd3-44d5-9302-5bfcc740f46e"));
            case STREET -> res = noteRepository.findByPriceAndCategoryAndStreet(minPrice, maxPrice, categoriesId,fias);
            case CITY -> res = noteRepository.findByPriceAndCategoryAndCity(minPrice, maxPrice, categoriesId,fias);
            case SETTLEMENT -> res = noteRepository.findByPriceAndCategoryAndSettlement(minPrice, maxPrice, categoriesId,fias);
            case AREA -> res = noteRepository.findByPriceAndCategoryAndArea(minPrice, maxPrice, categoriesId,fias);
            case REGION -> res = noteRepository.findByPriceAndCategoryAndRegion(minPrice, maxPrice, categoriesId,fias);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };

        return res;
    }

    public List<Note> relevant(SearchDto searchDto, List<Note> notes){
        notes = new ArrayList<>(notes);
        List<String> inKeywords = List.of(searchDto.getSearchQuery().split(" "));
        var f = new Function<List<String>, Double>() {
            JaroWinkler similarity = new JaroWinkler();

            @Override
            public Double apply(List<String> strings) {
                final Double COEFF_SIM = 0.95;

                int countAcceptWords = 0;
                for(var keyWord : inKeywords){
                    if(strings.stream().anyMatch((o) -> similarity.similarity(keyWord, o) > COEFF_SIM)){
                        countAcceptWords++;
                    }
                }
                return ((double)countAcceptWords)/inKeywords.size();
            }
        };

        notes.sort((o1, o2) -> {
            List<String> agr1 = new ArrayList<>(List.of(o1.getHeadline().split(" ")));
            agr1.addAll(List.of(o1.getDescribe().split(" ")));
            List<String> agr2 = new ArrayList<>(List.of(o2.getHeadline().split(" ")));
            agr2.addAll(List.of(o2.getDescribe().split(" ")));

            return f.apply(agr1) < f.apply(agr2) ? 1 : -1;
        });

        return notes;
    }
}
