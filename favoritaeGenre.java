package OA2;
 /*
    Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.

Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.

The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.

Example 1:

Input:
userSongs = {
   "David": ["song1", "song2", "song3", "song4", "song8"],
   "Emma":  ["song5", "song6", "song7"]
},
songGenres = {
   "Rock":    ["song1", "song3"],
   "Dubstep": ["song7"],
   "Techno":  ["song2", "song4"],
   "Pop":     ["song5", "song6"],
   "Jazz":    ["song8", "song9"]
}

Output: {
   "David": ["Rock", "Techno"],
   "Emma":  ["Pop"]
}

Explanation:
David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
Example 2:

Input:
userSongs = {
   "David": ["song1", "song2"],
   "Emma":  ["song3", "song4"]
},
songGenres = {}

Output: {
   "David": [],
   "Emma":  []
}
     */

/**
 *
 * TODO: Write a test to test this code
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class favoritaeGenre {


    public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {


        Map<String,List<String>> result = new HashMap<>();
        Map<String,String> songsTogenre = new HashMap<>();


        //deal with conner case, user map is null or user map is empty.

        if (userMap == null) {
            return result;
        }

        //deal with conner case, songGenre map is empty or null.
        if (genreMap == null || genreMap.size() == 0) {
            for (String user:userMap.keySet()) {
                result.put(user,new ArrayList<>());
            }
            return result;
        }


        //build a new map use song as key and genre as value;
        for (String genre:genreMap.keySet()) {
            //check 这个genre里面没有song。
            if (genreMap.get(genre)!= null && genreMap.get(genre).size() != 0) {
                List<String> songs = genreMap.get(genre);
                for (String song:songs) {
                    songsTogenre.put(song,genre);
                }
            }
        }

        // define a new map for every user, use genre as key and number of songs in that genre as value.
        Map<String,Integer> count ;

        int max;
        for (String user:userMap.keySet()) {
            max = 0;
            count = new HashMap<>();


            result.put(user,new ArrayList<>());

            //get this user's songs list.
            List<String> userSongsList = userMap.get(user);


            for (String userSong:userSongsList) {
                //check conner case : book must have a genre.
                if (songsTogenre.containsKey(userSong)){
                    String genre = songsTogenre.get(userSong);
                    int number = count.getOrDefault(genre,0);
                    max = Math.max(max,number+1);
                    count.put(genre,number + 1);
                }
            }

            for (String userGenre:count.keySet()) {
                if (max != 0 && count.get(userGenre) == max) {
                    result.get(user).add(userGenre);
                }
            }
        }

        return result;
    }
}
