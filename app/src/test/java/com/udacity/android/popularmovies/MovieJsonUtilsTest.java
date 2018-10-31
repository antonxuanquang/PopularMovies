package com.udacity.android.popularmovies;

import com.udacity.android.popularmovies.data.Movie;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import utils.MovieDataJsonUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieJsonUtilsTest {
    private static final String JSON = "{\"page\":1,\"total_results\":19834,\"total_pages\":992,\"results\":[{\"vote_count\":943,\"id\":335983,\"video\":false,\"vote_average\":6.7,\"title\":\"Venom\",\"popularity\":636.609,\"poster_path\":\"\\/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg\",\"original_language\":\"en\",\"original_title\":\"Venom\",\"genre_ids\":[27,878,28,53,35],\"backdrop_path\":\"\\/VuukZLgaCrho2Ar8Scl9HtV3yD.jpg\",\"adult\":false,\"overview\":\"When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.\",\"release_date\":\"2018-10-03\"},{\"vote_count\":423,\"id\":332562,\"video\":false,\"vote_average\":7.4,\"title\":\"A Star Is Born\",\"popularity\":284.682,\"poster_path\":\"\\/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg\",\"original_language\":\"en\",\"original_title\":\"A Star Is Born\",\"genre_ids\":[18,10402,10749],\"backdrop_path\":\"\\/840rbblaLc4SVxm8gF3DNdJ0YAE.jpg\",\"adult\":false,\"overview\":\"Seasoned musician Jackson Maine discovers—and falls in love with—struggling artist Ally. She has just about given up on her dream to make it big as a singer—until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.\",\"release_date\":\"2018-10-03\"},{\"vote_count\":523,\"id\":346910,\"video\":false,\"vote_average\":5.3,\"title\":\"The Predator\",\"popularity\":243.845,\"poster_path\":\"\\/wMq9kQXTeQCHUZOG4fAe5cAxyUA.jpg\",\"original_language\":\"en\",\"original_title\":\"The Predator\",\"genre_ids\":[27,878,28,35],\"backdrop_path\":\"\\/f4E0ocYeToEuXvczZv6QArrMDJ.jpg\",\"adult\":false,\"overview\":\"From the outer reaches of space to the small-town streets of suburbia, the hunt comes home. Now, the universe’s most lethal hunters are stronger, smarter and deadlier than ever before, having genetically upgraded themselves with DNA from other species. When a young boy accidentally triggers their return to Earth, only a ragtag crew of ex-soldiers and a disgruntled science teacher can prevent the end of the human race.\",\"release_date\":\"2018-09-13\"},{\"vote_count\":2655,\"id\":363088,\"video\":false,\"vote_average\":6.9,\"title\":\"Ant-Man and the Wasp\",\"popularity\":197.284,\"poster_path\":\"\\/rv1AWImgx386ULjcf62VYaW8zSt.jpg\",\"original_language\":\"en\",\"original_title\":\"Ant-Man and the Wasp\",\"genre_ids\":[28,12,878],\"backdrop_path\":\"\\/6P3c80EOm7BodndGBUAJHHsHKrp.jpg\",\"adult\":false,\"overview\":\"Just when his time under house arrest is about to end, Scott Lang puts again his freedom at risk to help Hope van Dyne and Dr. Hank Pym dive into the quantum realm and try to accomplish, against time and any chance of success, a very dangerous rescue mission.\",\"release_date\":\"2018-07-04\"},{\"vote_count\":1064,\"id\":439079,\"video\":false,\"vote_average\":5.9,\"title\":\"The Nun\",\"popularity\":173.24,\"poster_path\":\"\\/sFC1ElvoKGdHJIWRpNB3xWJ9lJA.jpg\",\"original_language\":\"en\",\"original_title\":\"The Nun\",\"genre_ids\":[27,9648,53],\"backdrop_path\":\"\\/fgsHxz21B27hOOqQBiw9L6yWcM7.jpg\",\"adult\":false,\"overview\":\"When a young nun at a cloistered abbey in Romania takes her own life, a priest with a haunted past and a novitiate on the threshold of her final vows are sent by the Vatican to investigate. Together they uncover the order’s unholy secret. Risking not only their lives but their faith and their very souls, they confront a malevolent force in the form of the same demonic nun that first terrorized audiences in “The Conjuring 2,” as the abbey becomes a horrific battleground between the living and the damned.\",\"release_date\":\"2018-09-05\"},{\"vote_count\":97,\"id\":463272,\"video\":false,\"vote_average\":6.2,\"title\":\"Johnny English Strikes Again\",\"popularity\":160.725,\"poster_path\":\"\\/tCBxnZwLiY1BOKw3tH6AxHZdqPh.jpg\",\"original_language\":\"en\",\"original_title\":\"Johnny English Strikes Again\",\"genre_ids\":[12,10751,28,35],\"backdrop_path\":\"\\/yCOLqh5MOGyYdo58Ap0aWvKop9h.jpg\",\"adult\":false,\"overview\":\"Disaster strikes when a criminal mastermind reveals the identities of all active undercover agents in Britain. The secret service can now rely on only one man—Johnny English. Currently teaching at a minor prep school, Johnny springs back into action to find the mysterious hacker. For this mission to succeed, he’ll need all of his skills—what few he has—as the man with yesterday’s analogue methods faces off against tomorrow’s digital technology.\",\"release_date\":\"2018-09-13\"},{\"vote_count\":8682,\"id\":299536,\"video\":false,\"vote_average\":8.3,\"title\":\"Avengers: Infinity War\",\"popularity\":157.107,\"poster_path\":\"\\/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg\",\"original_language\":\"en\",\"original_title\":\"Avengers: Infinity War\",\"genre_ids\":[12,878,28,14],\"backdrop_path\":\"\\/lmZFxXgJE3vgrciwuDib0N8CfQo.jpg\",\"adult\":false,\"overview\":\"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.\",\"release_date\":\"2018-04-25\"},{\"vote_count\":111,\"id\":347375,\"video\":false,\"vote_average\":5.7,\"title\":\"Mile 22\",\"popularity\":116.018,\"poster_path\":\"\\/dT1XzjxRDR56xOm4Ph0INV4EmWJ.jpg\",\"original_language\":\"en\",\"original_title\":\"Mile 22\",\"genre_ids\":[28],\"backdrop_path\":\"\\/eTwftrInxzZTSLUkX5hoifOczKJ.jpg\",\"adult\":false,\"overview\":\"A CIA field officer and an Indonesian police officer are forced to work together in confronting political corruption. An informant must be moved twenty-two miles to safety.\",\"release_date\":\"2018-08-16\"},{\"vote_count\":3859,\"id\":351286,\"video\":false,\"vote_average\":6.5,\"title\":\"Jurassic World: Fallen Kingdom\",\"popularity\":112.746,\"poster_path\":\"\\/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg\",\"original_language\":\"en\",\"original_title\":\"Jurassic World: Fallen Kingdom\",\"genre_ids\":[28,12,878,27],\"backdrop_path\":\"\\/3s9O5af2xWKWR5JzP2iJZpZeQQg.jpg\",\"adult\":false,\"overview\":\"Three years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the isla Nublar, so Claire Dearing, the former park manager, recruits Owen Grady to help prevent the extinction of the dinosaurs once again.\",\"release_date\":\"2018-06-06\"},{\"vote_count\":8506,\"id\":284054,\"video\":false,\"vote_average\":7.4,\"title\":\"Black Panther\",\"popularity\":112.024,\"poster_path\":\"\\/uxzzxijgPIY7slzFvMotPv8wjKA.jpg\",\"original_language\":\"en\",\"original_title\":\"Black Panther\",\"genre_ids\":[28,12,14,878],\"backdrop_path\":\"\\/b6ZJZHUdMEFECvGiDpJjlfUWela.jpg\",\"adult\":false,\"overview\":\"King T'Challa returns home from America to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne by factions within his own country as well as without. Using powers reserved to Wakandan kings, T'Challa assumes the Black Panther mantel to join with girlfriend Nakia, the queen-mother, his princess-kid sister, members of the Dora Milaje (the Wakandan 'special forces') and an American secret agent, to prevent Wakanda from being dragged into a world war.\",\"release_date\":\"2018-02-13\"},{\"vote_count\":1177,\"id\":345940,\"video\":false,\"vote_average\":6,\"title\":\"The Meg\",\"popularity\":108.616,\"poster_path\":\"\\/eyWICPcxOuTcDDDbTMOZawoOn8d.jpg\",\"original_language\":\"en\",\"original_title\":\"The Meg\",\"genre_ids\":[28,878,53],\"backdrop_path\":\"\\/rH79sB6Nkx4cMW3JzsUy7wK0rhX.jpg\",\"adult\":false,\"overview\":\"A deep sea submersible pilot revisits his past fears in the Mariana Trench, and accidentally unleashes the seventy foot ancestor of the Great White Shark believed to be extinct.\",\"release_date\":\"2018-08-09\"},{\"vote_count\":113,\"id\":443463,\"video\":false,\"vote_average\":6.9,\"title\":\"Leave No Trace\",\"popularity\":108.445,\"poster_path\":\"\\/awGdHLQdPARAWyiT4hzu1ZFdsHO.jpg\",\"original_language\":\"en\",\"original_title\":\"Leave No Trace\",\"genre_ids\":[18],\"backdrop_path\":\"\\/c5GHB444LQShWnO29ulpFMLo4Am.jpg\",\"adult\":false,\"overview\":\"A father and daughter live a perfect but mysterious existence in Forest Park, a beautiful nature reserve near Portland, Oregon, rarely making contact with the world. But when a small mistake tips them off to authorities, they are sent on an increasingly erratic journey in search of a place to call their own.\",\"release_date\":\"2018-06-29\"},{\"vote_count\":70,\"id\":446894,\"video\":false,\"vote_average\":6.7,\"title\":\"Smallfoot\",\"popularity\":106.71,\"poster_path\":\"\\/4nKoB6wMVXfsYgRZK5lHZ5VMQ6J.jpg\",\"original_language\":\"en\",\"original_title\":\"Smallfoot\",\"genre_ids\":[35,16,10751,12,14],\"backdrop_path\":\"\\/7t88SoT3Dd8DhGnQuVoSbMNUl3W.jpg\",\"adult\":false,\"overview\":\"A bright young yeti finds something he thought didn't exist—a human. News of this “smallfoot” throws the simple yeti community into an uproar over what else might be out there in the big world beyond their snowy village.\",\"release_date\":\"2018-09-20\"},{\"vote_count\":40,\"id\":507569,\"video\":false,\"vote_average\":5.9,\"title\":\"The Seven Deadly Sins: Prisoners of the Sky\",\"popularity\":101.174,\"poster_path\":\"\\/r6pPUVUKU5eIpYj4oEzidk5ZibB.jpg\",\"original_language\":\"ja\",\"original_title\":\"劇場版 七つの大罪 天空の囚われ人\",\"genre_ids\":[28,12,14,16],\"backdrop_path\":\"\\/uKwOX7MtKlAaGeCQe6c4jc1vZpj.jpg\",\"adult\":false,\"overview\":\"Traveling in search of the rare ingredient, “sky fish”  Meliodas and Hawk arrive at a palace that floats above the clouds. The people there are busy preparing a ceremony, meant to protect their home from a ferocious beast that awakens once every 3,000 years. But before the ritual is complete, the Six Knights of Black—a Demon Clan army—removes the seal on the beast, threatening the lives of everyone in the Sky Palace.\",\"release_date\":\"2018-08-18\"},{\"vote_count\":2130,\"id\":348350,\"video\":false,\"vote_average\":6.7,\"title\":\"Solo: A Star Wars Story\",\"popularity\":95.812,\"poster_path\":\"\\/3IGbjc5ZC5yxim5W0sFING2kdcz.jpg\",\"original_language\":\"en\",\"original_title\":\"Solo: A Star Wars Story\",\"genre_ids\":[28,12,878,80,37,10749],\"backdrop_path\":\"\\/5DUqFLgkLsJxyqPCAcgTivZy2SX.jpg\",\"adult\":false,\"overview\":\"Through a series of daring escapades deep within a dark and dangerous criminal underworld, Han Solo meets his mighty future copilot Chewbacca and encounters the notorious gambler Lando Calrissian.\",\"release_date\":\"2018-05-15\"},{\"vote_count\":59,\"id\":369972,\"video\":false,\"vote_average\":5.9,\"title\":\"First Man\",\"popularity\":91.542,\"poster_path\":\"\\/i91mfvFcPPlaegcbOyjGgiWfZzh.jpg\",\"original_language\":\"en\",\"original_title\":\"First Man\",\"genre_ids\":[36,18],\"backdrop_path\":\"\\/z1FkoHO7bz40S4JiptWHSYoPpxq.jpg\",\"adult\":false,\"overview\":\"A look at the life of the astronaut, Neil Armstrong, and the legendary space mission that led him to become the first man to walk on the Moon on July 20, 1969.\",\"release_date\":\"2018-10-11\"},{\"vote_count\":0,\"id\":508830,\"video\":false,\"vote_average\":0,\"title\":\"Kamen Rider Amazons The Movie: The Final Judgement\",\"popularity\":87.78,\"poster_path\":\"\\/putlftM2cvILAwSmOn6gO0G0C7s.jpg\",\"original_language\":\"ja\",\"original_title\":\"仮面ライダーアマゾンズ THE MOVIE 最後ノ審判\",\"genre_ids\":[28,12,878,27],\"backdrop_path\":\"\\/p8RXmzb2dvF6P8oDKRAUom1W2ro.jpg\",\"adult\":false,\"overview\":\"The “Amazon Livestock Project,” a mysterious care facility, the Amazon extermination organization 4C (Competitive Creatures Control Center) pursuing Haruka Misuzawa . . . all come together in a tale of symbiosis and competition; the mortal combat between the carnivore and herbivore. And, finally, an end to the troubles and conflicts of the Riders, two beings confronting a fierce fate.\",\"release_date\":\"2018-05-19\"},{\"vote_count\":780,\"id\":458423,\"video\":false,\"vote_average\":7.6,\"title\":\"Mamma Mia! Here We Go Again\",\"popularity\":87.699,\"poster_path\":\"\\/aWicerX4Y7n7tUwRAVHsVcBBpj2.jpg\",\"original_language\":\"en\",\"original_title\":\"Mamma Mia! Here We Go Again\",\"genre_ids\":[35,10749,10402],\"backdrop_path\":\"\\/kAErJpVU4ul9R3VzroM8awWrlT2.jpg\",\"adult\":false,\"overview\":\"Five years after meeting her three fathers, Sophie Sheridan prepares to open her mother’s hotel. In 1979, young Donna Sheridan meets the men who each could be Sophie’s biological father.\",\"release_date\":\"2018-07-18\"},{\"vote_count\":5520,\"id\":383498,\"video\":false,\"vote_average\":7.5,\"title\":\"Deadpool 2\",\"popularity\":86.16,\"poster_path\":\"\\/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg\",\"original_language\":\"en\",\"original_title\":\"Deadpool 2\",\"genre_ids\":[28,35,878],\"backdrop_path\":\"\\/3P52oz9HPQWxcwHOwxtyrVV1LKi.jpg\",\"adult\":false,\"overview\":\"Wisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life.\",\"release_date\":\"2018-05-15\"},{\"vote_count\":967,\"id\":447200,\"video\":false,\"vote_average\":6.1,\"title\":\"Skyscraper\",\"popularity\":84.999,\"poster_path\":\"\\/5LYSsOPzuP13201qSzMjNxi8FxN.jpg\",\"original_language\":\"en\",\"original_title\":\"Skyscraper\",\"genre_ids\":[28,53,18],\"backdrop_path\":\"\\/oMKFQmoVgB69fyXfSMu0lGlHJP2.jpg\",\"adult\":false,\"overview\":\"Framed and on the run, a former FBI agent must save his family from a blazing fire in the world's tallest building.\",\"release_date\":\"2018-07-11\"}]}";

//    private final static String JSON = "{\n" +
//            "   \"page\":2,\n" +
//            "   \"total_results\":6157,\n" +
//            "   \"total_pages\":308,\n" +
//            "   \"results\":[\n" +
//            "      {\n" +
//            "         \"vote_count\":1551,\n" +
//            "         \"id\":12477,\n" +
//            "         \"video\":false,\n" +
//            "         \"vote_average\":8.4,\n" +
//            "         \"title\":\"Grave of the Fireflies\",\n" +
//            "         \"popularity\":0.01,\n" +
//            "         \"poster_path\":\"/4u1vptE8aXuzb5zauZTmikyectV.jpg\",\n" +
//            "         \"original_language\":\"ja\",\n" +
//            "         \"original_title\":\"火垂るの墓\",\n" +
//            "         \"genre_ids\":[\n" +
//            "            16,\n" +
//            "            18,\n" +
//            "            10752\n" +
//            "         ],\n" +
//            "         \"backdrop_path\":\"/fCUIuG7y4YKC3hofZ8wsj7zhCpR.jpg\",\n" +
//            "         \"adult\":false,\n" +
//            "         \"overview\":\"In the final months of World War II, 14-year-old Seita and his sister Setsuko are orphaned when their mother is killed during an air raid in Kobe, Japan. After a falling out with their aunt, they move into an abandoned bomb shelter. With no surviving relatives and their emergency rations depleted, Seita and Setsuko struggle to survive.\",\n" +
//            "         \"release_date\":\"1988-04-16\"\n" +
//            "      },\n" +
//            "      {\n" +
//            "         \"vote_count\":1187,\n" +
//            "         \"id\":346,\n" +
//            "         \"video\":false,\n" +
//            "         \"vote_average\":8.3,\n" +
//            "         \"title\":\"Seven Samurai\",\n" +
//            "         \"popularity\":12.017,\n" +
//            "         \"poster_path\":\"/v6xrz4fr92KY1oNC3HsEvrsvR1n.jpg\",\n" +
//            "         \"original_language\":\"ja\",\n" +
//            "         \"original_title\":\"七人の侍\",\n" +
//            "         \"genre_ids\":[\n" +
//            "            28,\n" +
//            "            18\n" +
//            "         ],\n" +
//            "         \"backdrop_path\":\"/61vLiK96sbXeHpQiMxI4CuqBA3z.jpg\",\n" +
//            "         \"adult\":false,\n" +
//            "         \"overview\":\"A samurai answers a village's request for protection after he falls on hard times. The town needs protection from bandits, so the samurai gathers six others to help him teach the people how to defend themselves, and the villagers provide the soldiers with food. A giant battle occurs when 40 bandits attack the village.\",\n" +
//            "         \"release_date\":\"1954-04-26\"\n" +
//            "      }\n" +
//            "   ]\n" +
//            "}";



    private Movie graveMovie;
    private Movie samuraiMovie;


    @Before
    public void setUp() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        graveMovie = new Movie(
                "Grave of the Fireflies",
                "/4u1vptE8aXuzb5zauZTmikyectV.jpg",
                1551,
                8.4,
                "/fCUIuG7y4YKC3hofZ8wsj7zhCpR.jpg",
                "A samurai answers a village's request for protection after he falls on hard times. The town needs protection from bandits, so the samurai gathers six others to help him teach the people how to defend themselves, and the villagers provide the soldiers with food. A giant battle occurs when 40 bandits attack the village.",
                "火垂るの墓",
                format.parse("1988-04-16")
        );
        samuraiMovie = new Movie(
                "Seven Samurai",
                "/v6xrz4fr92KY1oNC3HsEvrsvR1n.jpg",
                1187,
                8.3,
                "/61vLiK96sbXeHpQiMxI4CuqBA3z.jpg",
                "In the final months of World War II, 14-year-old Seita and his sister Setsuko are orphaned when their mother is killed during an air raid in Kobe, Japan. After a falling out with their aunt, they move into an abandoned bomb shelter. With no surviving relatives and their emergency rations depleted, Seita and Setsuko struggle to survive.",
                "七人の侍",
                format.parse("1954-04-26")
        );

    }

    @Test
    @Ignore("Android SDK junit doesn't mock new JSONObject() constructor")
    public void givenJSONString_parse_returnListOfMovieData() throws JSONException, ParseException {
        List<Movie> movieList = MovieDataJsonUtils.parse(JSON);

        assertThat(movieList).containsExactly(graveMovie, samuraiMovie);
    }
}