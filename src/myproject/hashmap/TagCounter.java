package myproject.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class TagCounter {

    /**
     * Goes through each twitter message and tabulates the count for each hashtag used
     * @param tags
     */
    private void countTags(String[] tags) {

        // This will store the unique tags and their counts
        Map<String, Integer> tagCounts = new HashMap<>();

        // go through each message and put the hashtags in the map along with their count
        Arrays.stream(tags)
            .forEach(tag -> {
                String[] words = tag.split(" ");
                Arrays.stream(words).forEach(word -> {
                    if (word.startsWith("#")) {
                        if (tagCounts.containsKey(word)) {
                            tagCounts.replace(word, tagCounts.get(word) + 1);
                        } else {
                            tagCounts.put(word, 1);
                        }
                    }
                });
            });

        // convert the hashmap into a list to sort
        Set<Map.Entry<String, Integer>> entrySet = tagCounts.entrySet();
        ArrayList<Map.Entry<String, Integer>> listOfEntry = new ArrayList<>(entrySet);

        // sort it
        listOfEntry.sort((Map.Entry<String, Integer> t1, Map.Entry<String, Integer> t2) -> {
            return t2.getValue().compareTo(t1.getValue());
        });

        listOfEntry.subList(0, 5).forEach(item -> {
            System.out.println(item.getKey() + "=" + item.getValue());
        });


    }

    public static void main(String[] args) {

        String[] twitterFeed = {
                "Loving the bears #gobears",
                "#SupportKidsInDirt is a great tag that supports kids in dirt.",
                "Early morning #coffee makes me love life a little",
                "#throwbackthursday Coolest fan we’ve ever seen.",
                "Time for a Royal Celebration! #Royalbaby",
                "Girl from the South Side and former First Lady. Wife, mother, dog lover. Always hugger-in-chief. #IAmBecoming",
                "To win: http://GaSenate.com. #AllInForVoting",
                "Nothing good about #throwbackthursday",
                "The #49ersFoundation Cook-Off: Tailgate",
                "The #49ers have waived/failed physical LB Kiko Alonso and placed two on the Reserve/COVID-19 list.",
                "#coffee is what I think of in the morning",
                "seriously #coffee",
                "Former #SanFrancisco Mayor Willie Brown says there are several good options",
                "Advance ticket purchase is required, and #glowfari nights are already starting to sell out.",
                "#glowfari glow is here",
                "I love my #coffee vacation in Hawaii",
                "I love me some #SanFrancisco"
        };

        TagCounter tagCounter = new TagCounter();
        tagCounter.countTags(twitterFeed);
    }
}
