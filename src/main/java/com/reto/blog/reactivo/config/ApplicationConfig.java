package com.reto.blog.reactivo.config;

import com.reto.blog.reactivo.entities.*;
import com.reto.blog.reactivo.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationConfig implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Creating authors");
        this.cleanAuthorData();
        this.loadAuthorData();

        logger.info("Creating users");
        this.cleanUserData();
        this.loadUserData();

        logger.info("Creating blogs");
        this.cleanBlogData();
        this.loadBlogData();

        logger.info("Creating posts");
        this.cleanPostData();
        this.loadPostData();

        logger.info("Creating comments");
        this.cleanCommentData();
        this.loadCommentData();

        logger.info("Creating reactions");
        this.cleanReactionData();
        this.loadReactionData();
    }

    private void cleanAuthorData() {
        this.authorRepository.deleteAll().block();
    }

    private void loadAuthorData() {
        this.authorRepository.save(Author.builder()
                .id("6224e5ce19c03918bd6c9501")
                .name("Carlos L. Ives")
                .email("CarlosLIves@gustggr.com")
                .phone("408-624-2968")
                .birthDate(LocalDate.parse("1936-10-25"))
                .build()).doOnNext(author -> logger.info("{}", author)).block();

        this.authorRepository.save(Author.builder()
                .id("6224e5ce19c03918bd6c9502")
                .name("Harry P. Carr")
                .email("HarryPCarr@gustggr.com")
                .phone("830-262-9435")
                .birthDate(LocalDate.parse("1984-08-29"))
                .build()).doOnNext(author -> logger.info("{}", author)).block();

        this.authorRepository.save(Author.builder()
                .id("6224e5ce19c03918bd6c9503")
                .name("Charles J. Bender")
                .email("CharlesJBender@gustggr.com")
                .phone("970-364-9563")
                .birthDate(LocalDate.parse("1953-07-17"))
                .build()).doOnNext(author -> logger.info("{}", author)).block();

        this.authorRepository.save(Author.builder()
                .id("6224e5ce19c03918bd6c9504")
                .name("Mark R. Castle")
                .email("MarkRCastle@gustggr.com")
                .phone("460-954-6932")
                .birthDate(LocalDate.parse("1993-03-01"))
                .build()).doOnNext(author -> logger.info("{}", author)).block();

        this.authorRepository.save(Author.builder()
                .id("6224e5ce19c03918bd6c9505")
                .name("Kris A. Burchett")
                .email("KrisABurchett@gustggr.com")
                .phone("330-744-9813")
                .birthDate(LocalDate.parse("2005-11-21"))
                .build()).doOnNext(author -> logger.info("{}", author)).block();
    }

    private void cleanUserData() {
        this.userRepository.deleteAll().block();
    }

    private void loadUserData() {
        this.userRepository.save(User.builder()
                .id("6224f87fec483b6a7655e251")
                .username("CarlosLIves")
                .password("Car1osLIve5!#")
                .authorId("6224e5ce19c03918bd6c9501")
                .build()).doOnNext(user -> logger.info("{}", user)).block();

        this.userRepository.save(User.builder()
                .id("6224f87fec483b6a7655e252")
                .username("HarryPCarr")
                .password("H9rr7PC9rr!#")
                .authorId("6224e5ce19c03918bd6c9502")
                .build()).doOnNext(user -> logger.info("{}", user)).block();

        this.userRepository.save(User.builder()
                .id("6224f87fec483b6a7655e253")
                .username("CharlesJBender")
                .password("C4ar1esJBen6er!#")
                .authorId("6224e5ce19c03918bd6c9503")
                .build()).doOnNext(user -> logger.info("{}", user)).block();

        this.userRepository.save(User.builder()
                .id("6224f87fec483b6a7655e254")
                .username("MarkRCastle")
                .password("Ma5kRC9stl6!#")
                .build()).doOnNext(user -> logger.info("{}", user)).block();
    }

    private void cleanBlogData() {
        this.blogRepository.deleteAll().block();
    }

    private void loadBlogData() {
        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce11")
                .name("Live Apple Event - Apple September Event 2017 - iPhone 8, iPhone X, iOS 11 - Apple Keynote")
                .description("apple events|apple event|iphone 8|iphone x|iphone 8 plus|iphone 7s|iphone 7s plus|ios 11")
                .url("https://i.ytimg.com/vi/WoPtuVbaSKQ/default.jpg")
                .status("activo")
                .authorId("6224e5ce19c03918bd6c9501")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce12")
                .name("Holly and Phillip Meet Samantha the Sex Robot | This Morning")
                .description("this morning|interview|holly willoughby|phillip schofield|ruth langsford|eamonn holmes|chat shows - ...")
                .url("https://i.ytimg.com/vi/3-yamPXZQtU/default.jpg")
                .status("inactivo")
                .authorId("6224e5ce19c03918bd6c9501")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce13")
                .name("My DNA Test Results! Im WHAT?!")
                .description("emmablackery|emma blackery|emma|blackery|british vlogger|british youtuber|female vlogger|birdyboots|...")
                .url("https://i.ytimg.com/vi/zcqZHYo7ONs/default.jpg")
                .status("activo")
                .authorId("6224e5ce19c03918bd6c9502")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce14")
                .name("getting into a conversation in a language you dont actually speak that well")
                .description("skit|korean|language|conversation|esl|japanese|foreign|communication|dont speak|struggle|foreigner|...")
                .url("https://i.ytimg.com/vi/DUFBEamEF0Q/default.jpg")
                .status("inactivo")
                .authorId("6224e5ce19c03918bd6c9502")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce15")
                .name("REVEALED - FIFA 18 stats for Chelseas Hazard, Luiz & Christensen!")
                .description("how to|cooking|recipe|kitchen|chicken|chicken breast|juicy chicken breast|baked chicken breast|hummu...")
                .url("https://i.ytimg.com/vi/JsTptu56GM8/default.jpg")
                .status("activo")
                .authorId("6224e5ce19c03918bd6c9503")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce16")
                .name("Juicy Chicken Breast - You Suck at Cooking (episode 65)")
                .description("dude perfect|dude perfect stereotypes|dude perfect water bottle flip|bottle flip|water bottle flip|d...")
                .url("https://i.ytimg.com/vi/nZHZMVlc9A0/default.jpg")
                .status("inactivo")
                .authorId("6224e5ce19c03918bd6c9503")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce17")
                .name("Action Bronson and Sean Evans Have a Sandwich Showdown, Judged by Mario Batali | Sean in the Wild")
                .description("First we feast|fwf|firstwefeast|food|food porn|cook|cooking|chef|kitchen|recipe|cocktail|bartender|c...")
                .url("https://i.ytimg.com/vi/X0U8Awfizoc/default.jpg")
                .status("activo")
                .authorId("6224e5ce19c03918bd6c9503")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce18")
                .name("Whats Actually the Plane of the Future")
                .description("nile wilson|nile wilson gymnastics|nile wilson olympics|olympic gymnast|amazing gymnastics|gymnastic...")
                .url("https://i.ytimg.com/vi/bp6uJJJMaLs/default.jpg")
                .status("inactivo")
                .authorId("6224e5ce19c03918bd6c9504")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();

        this.blogRepository.save(Blog.builder()
                .id("6224ec5bc634f04c0890ce19")
                .name("Drowning for Power - Rooster Teeth Animated Adventures")
                .description("samantha|maria|sammi|shopping|fenty|beauty|back|at|the|gym|handmaids|tale")
                .url("https://i.ytimg.com/vi/wP97gsUp4cM/default.jpg")
                .status("activo")
                .authorId("6224e5ce19c03918bd6c9504")
                .build()).doOnNext(blog -> logger.info("{}", blog)).block();
    }

    private void cleanPostData() {
        this.postRepository.deleteAll().block();
    }

    private void loadPostData() {
        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b21")
                .title("Taken at the Flood")
                .date(LocalDate.parse("1912-07-25"))
                .status("borrador")
                .content("One of Sidney Sheldons most popular and bestselling titles, repackaged and reissued for a new gener...")
                .blogId("6224ec5bc634f04c0890ce11")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b22")
                .title("The voyage of the Dawn Treader")
                .date(LocalDate.parse("1983-03-07"))
                .status("publicado")
                .content("Kate Blackwell is an enigma and one of the most powerful women in the world. But at her ninetieth bi...")
                .blogId("6224ec5bc634f04c0890ce11")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b23")
                .title("Heart Songs and Other Stories")
                .date(LocalDate.parse("1943-08-14"))
                .status("borrador")
                .content("A new-cover reissue of the fourth book in the bestselling five-volume sf series created by the world...")
                .blogId("6224ec5bc634f04c0890ce13")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b24")
                .title("The Wee Free Men")
                .date(LocalDate.parse("1994-09-27"))
                .status("publicado")
                .content("Emma Watson a research physician has been training for the mission of a lifetime: to study living or...")
                .blogId("6224ec5bc634f04c0890ce13")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b25")
                .title("Mars and Venus Book of Days")
                .date(LocalDate.parse("1982-10-05"))
                .status("borrador")
                .content("Spares - human clones, the ultimate health insurance. An eye for an eye - but some people are doing ...")
                .blogId("6224ec5bc634f04c0890ce15")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b26")
                .title("The Last of the Really Great Whangdoodles")
                .date(LocalDate.parse("1958-10-30"))
                .status("publicado")
                .content("Miss Marple featured in 20 short stories, published in a number of different collections in Britain ...")
                .blogId("6224ec5bc634f04c0890ce15")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b27")
                .title("The Forever War")
                .date(LocalDate.parse("2006-04-15"))
                .status("borrador")
                .content("Sauron has gathered the Rings of Power - the means by which he will be able to rule the world. All h...")
                .blogId("6224ec5bc634f04c0890ce15")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b28")
                .title("A Nose for Murder")
                .date(LocalDate.parse("2015-02-24"))
                .status("publicado")
                .content("A young drifter finds more than he bargained for when he agrees to deliver a parcel to an English co...")
                .blogId("6224ec5bc634f04c0890ce15")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b29")
                .title("The Kindness of Strangers")
                .date(LocalDate.parse("2001-01-27"))
                .status("borrador")
                .content("In an absorbing narrative about personalities and social history, Menand discusses the Metaphysical ...")
                .blogId("6224ec5bc634f04c0890ce17")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b2a")
                .title("The Black Dahlia Files")
                .date(LocalDate.parse("2010-01-30"))
                .status("publicado")
                .content("Suggests a theory of presidential power, and tests it against the events in the administrations of t...")
                .blogId("6224ec5bc634f04c0890ce17")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b2b")
                .title("Touching the Void")
                .date(LocalDate.parse("1972-11-12"))
                .status("borrador")
                .content("A blending of scholarly research and interviews with many of the figures who launched the civil righ...")
                .blogId("6224ec5bc634f04c0890ce17")
                .build()).doOnNext(post -> logger.info("{}", post)).block();

        this.postRepository.save(Post.builder()
                .id("62250094e73b5f45670b6b2c")
                .title("The Problem of Pain")
                .date(LocalDate.parse("2017-08-19"))
                .status("publicado")
                .content("Armed with the latest gifts of advanced technology, a California scientist, a ruthless corporation a...")
                .blogId("6224ec5bc634f04c0890ce19")
                .build()).doOnNext(post -> logger.info("{}", post)).block();
    }

    private void cleanCommentData() {
        this.commentRepository.deleteAll().block();
    }

    private void loadCommentData() {
        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be990")
                .date(LocalDate.parse("2010-09-22"))
                .comment("Its more accurate to call it the M+ (1000) because the price is closer than calling it the X (10).")
                .state("publicado")
                .userId("6224f87fec483b6a7655e251")
                .postId("62250094e73b5f45670b6b22")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be991")
                .date(LocalDate.parse("2010-10-23"))
                .comment("They really just took the samsung s8 and named it iphone x or whatever")
                .state("actualizado")
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b22")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be992")
                .date(LocalDate.parse("2013-03-22"))
                .comment("For god sake its better than my ps4pro")
                .state("publicado")
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b24")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be993")
                .date(LocalDate.parse("2014-06-16"))
                .comment("you can keep looking at it , time to time. They will keep updating it.")
                .state("actualizado")
                .userId("6224f87fec483b6a7655e253")
                .postId("62250094e73b5f45670b6b24")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be994")
                .date(LocalDate.parse("2015-07-21"))
                .comment("i was actually interested in this...and then you said how much it was lol")
                .state("publicado")
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b24")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be995")
                .date(LocalDate.parse("2015-09-08"))
                .comment("It is a conspiracy.... It is the NWO collecting everyones generic data so they know who to clone an...")
                .state("actualizado")
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b26")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be996")
                .date(LocalDate.parse("2015-11-22"))
                .comment("northwestern european is pretty vague cause that would include UK as well")
                .state("publicado")
                .userId("6224f87fec483b6a7655e251")
                .postId("62250094e73b5f45670b6b28")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be997")
                .date(LocalDate.parse("2017-04-02"))
                .comment("Her genetics are pure, we must breed her!")
                .state("actualizado")
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b28")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be998")
                .date(LocalDate.parse("2017-09-28"))
                .comment("Why people call themselves a certain nationality if they dont even know the language?")
                .state("publicado")
                .userId("6224f87fec483b6a7655e253")
                .postId("62250094e73b5f45670b6b28")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be999")
                .date(LocalDate.parse("2019-12-01"))
                .comment("Im sure hazard already googled his")
                .state("actualizado")
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b28")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be99a")
                .date(LocalDate.parse("2017-02-06"))
                .comment("See even the footballers say the rating is wrong")
                .state("publicado")
                .userId("6224f87fec483b6a7655e251")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be99b")
                .date(LocalDate.parse("2012-07-31"))
                .comment("I was curious to see where that ladybug was gonna go.")
                .state("actualizado")
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be99c")
                .date(LocalDate.parse("2014-11-11"))
                .comment("That juice is really the salt water that gets injected into the dead bird youre about to eat. The b...")
                .state("publicado")
                .userId("6224f87fec483b6a7655e253")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be99d")
                .date(LocalDate.parse("2015-12-20"))
                .comment("I can only imagine how many times they had to film that pool table trick shot.")
                .state("actualizado")
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

        this.commentRepository.save(Comment.builder()
                .id("6225863ffa61842c291be99e")
                .date(LocalDate.parse("2021-03-01"))
                .comment("Lets just say their engineers")
                .state("publicado")
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b2c")
                .build()).doOnNext(comment -> logger.info("{}", comment)).block();

    }

    private void cleanReactionData() {
        this.reactionRepository.deleteAll().block();
    }

    private void loadReactionData() {
        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f60")
                .type("like")
                .date(LocalDate.parse("2021-10-07"))
                .userId("6224f87fec483b6a7655e251")
                .postId("62250094e73b5f45670b6b22")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f61")
                .type("like")
                .date(LocalDate.parse("2020-07-22"))
                .userId("6224f87fec483b6a7655e253")
                .postId("62250094e73b5f45670b6b22")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f62")
                .type("like")
                .date(LocalDate.parse("2019-11-15"))
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b22")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f63")
                .type("like")
                .date(LocalDate.parse("2019-05-07"))
                .userId("6224f87fec483b6a7655e251")
                .postId("62250094e73b5f45670b6b24")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f64")
                .type("like")
                .date(LocalDate.parse("2018-12-19"))
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b24")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f65")
                .type("like")
                .date(LocalDate.parse("2017-02-17"))
                .userId("6224f87fec483b6a7655e253")
                .postId("62250094e73b5f45670b6b24")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f66")
                .type("like")
                .date(LocalDate.parse("2021-09-29"))
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b24")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f67")
                .type("like")
                .date(LocalDate.parse("2010-09-29"))
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b26")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f68")
                .type("like")
                .date(LocalDate.parse("2015-09-29"))
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b26")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f69")
                .type("like")
                .date(LocalDate.parse("2021-11-27"))
                .userId("6224f87fec483b6a7655e251")
                .postId("62250094e73b5f45670b6b28")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f6a")
                .type("like")
                .date(LocalDate.parse("2020-01-12"))
                .userId("6224f87fec483b6a7655e251")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f6b")
                .type("like")
                .date(LocalDate.parse("2018-10-21"))
                .userId("6224f87fec483b6a7655e252")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f6c")
                .type("like")
                .date(LocalDate.parse("2016-04-16"))
                .userId("6224f87fec483b6a7655e253")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

        this.reactionRepository.save(Reaction.builder()
                .id("62258b0b7e25bb25889d9f6d")
                .type("like")
                .date(LocalDate.parse("2014-08-15"))
                .userId("6224f87fec483b6a7655e254")
                .postId("62250094e73b5f45670b6b2a")
                .build()).doOnNext(reaction -> logger.info("{}", reaction)).block();

    }

}
