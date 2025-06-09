package ar.com.up.theater.manager.mapper;

import ar.com.up.theater.manager.dto.*;
import ar.com.up.theater.manager.model.Artist;
import ar.com.up.theater.manager.model.Hall;
import ar.com.up.theater.manager.model.Show;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TheaterMapper {
    @Mapping(target = "id",source = "hallId")
    HallDto hallToDto (Hall hall);


    HallSummaryDto hallToSummaryDto (Hall hall);
    @Mapping(target = "id",source = "artistId")
    ArtistDto artistToDto (Artist artist);

    @Mapping(target = "id",source = "showId")
    @Mapping(source = "date",
            target = "date",
            dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "price", source = "ticketDefaultPrice")
    @Mapping(target = "artists",source = "artistsSet")
    @Mapping(target = "duration",source = "minutes")
    ShowDto showToDto (Show show);



}
