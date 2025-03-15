package ee.avastaeesti.gameback.service.favourite;

import ee.avastaeesti.gameback.controller.favourite.dto.UserFavourite;
import ee.avastaeesti.gameback.persistence.favorite.Favourite;
import ee.avastaeesti.gameback.persistence.favorite.FavouriteRepository;
import ee.avastaeesti.gameback.status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavouriteRepository favouriteRepository;

    public List<UserFavourite> getUserFavourites(Integer userId) {
        List<Favourite> favourites = favouriteRepository.findFavouritesBy(userId, Status.ACTIVE.getCode());

        Integer indexCounter = 1;
        List<UserFavourite> userFavourites = new ArrayList<>();

        for (Favourite favourite : favourites) {
            UserFavourite userFavourite = new UserFavourite();
            userFavourite.setIndex(indexCounter);
            userFavourite.setLocationId(favourite.getLocation().getId());
            indexCounter++;
            userFavourites.add(userFavourite);
        }
        return userFavourites;
    }
}
