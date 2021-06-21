package com.smartdev.ufoss.config.DefaultDataConfig;

import com.smartdev.ufoss.entity.LessonEntity;
import com.smartdev.ufoss.repository.LessonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LessonConfig {

    @Bean
    CommandLineRunner lessonCommandLineRunner(LessonRepository lessonRepository) {
        return args -> {
            LessonEntity l1 = new LessonEntity(
                    "https://www.youtube.com/watch?v=C7dPqrmDWxs&list=PLGYPpIsdZKnLRU3hBKDmUBRdzVdM0rS0z",
                    "Pharrell Williams - Happy (Official Video)",
                    "Pharrell Williams  Dear  G  I  R  L  Tour Dates"
            );
            LessonEntity l2 = new LessonEntity(
                    "https://www.youtube.com/watch?v=9bZkp7q19f0",
                    "PSY - GANGNAM STYLE(강남스타일) M/V",
                    "YG Entertainment Inc. (Music)"
            );
            LessonEntity l3 = new LessonEntity(
                    "https://www.youtube.com/watch?v=fWNaR-rxAic",
                    "Carly Rae Jepsen - Call Me Maybe",
                    "Get E•MO•TION on iTunes now:Sign up for Carly Rae Jepsen news here: Music video by Carly Rae Jepsen performing Call Me Maybe. (C) 2011 604 Records Inc.\n" +
                            "#VEVOCertified on June 8, 2012."
            );
            LessonEntity l4 = new LessonEntity(
                    "https://www.youtube.com/watch?v=I-QfPUz1es8",
                    "Imagine Dragons - Bad Liar",
                    "Get Origins, ft. Natural, Zero, Machine and Bad Liar, out now: http://smarturl.it/OriginsID\n" +
                            "\n" +
                            "Directed By: Ryan Reichenfeld\n" +
                            "Dancer: Autumn Miller"
            );
            LessonEntity l5 = new LessonEntity(
                    "https://www.youtube.com/watch?v=gH476CxJxfg",
                    "Daniel Powter - Bad Day (Official Music Video) | Warner Vault",
                    "Bad Day by Daniel Powter from the album Daniel Powter © 2005\n" +
                            "\uD83D\uDD14  Subscribe & Turn on notifications to stay updated with new uploads!"
            );
            LessonEntity l6 = new LessonEntity(
                    "https://www.youtube.com/watch?v=dbK5bC9tmwM",
                    "[Vietsub+Lyrics] The Show - Lenka",
                    "I'm just a little bit caught in the middle\n" +
                            "Life is a maze and love is a riddle\n" +
                            "I don't know where to go, can't do it alone\n" +
                            "I've tried and I don't know why"
            );
            LessonEntity l7 = new LessonEntity(
                    "https://www.youtube.com/watch?v=gBmPZjOUppI",
                    "[Vietsub+Lyrics] Proud Of You - Fiona Fung",
                    "Love in your eyes\n" +
                            "Sitting silent by my side\n" +
                            "Going on, holding hand, walking trough the nights\n" +
                            "Hold me up, hol me tight, lift me up to touch the sky\n" +
                            "Teaching me to love with heart, helping me open my mind\n" +
                            "I can fly, i'm proud that I can fly"
            );
            LessonEntity l8 = new LessonEntity(
                    "https://www.youtube.com/watch?v=3gK_2XdjOdY",
                    "Titanic - My Heart Will Go On (Music Video)",
                    "Every night in my dreams\n" +
                            "I see you, I feel you\n" +
                            "That is how I know you go on"
            );
            LessonEntity l9 = new LessonEntity(
                    "https://www.youtube.com/watch?v=ulOb9gIGGd0",
                    "Westlife - My Love (Official Video)",
                    "An empty street, an empty house\n" +
                            "A hole inside my heart\n" +
                            "I'm all alone, the rooms are getting smaller."
            );
            LessonEntity l10 = new LessonEntity(
                    "https://www.youtube.com/watch?v=64VZUNTmGQM",
                    "PARK BOM - YOU AND I M/V",
                    "That was the most played song on the radio in Korea’s HISTORY for a good time! Truly a masterpiece as Park Bom herself described"
            );
            LessonEntity l11 = new LessonEntity(
                    "https://www.youtube.com/watch?v=-Plg9lj6YwQ",
                    "The way to kick Love",
                    "DuoQuyenTinhYeu"
            );
            LessonEntity l12 = new LessonEntity(
                    "https://www.youtube.com/watch?v=tLtxoa1RAyE",
                    "Aloha | English Version | Video Lyrics",
                    "Oh the candlelight shimmers in the night\n" +
                            "We promise our love with glasses of wine\n" +
                            "I will always be here to protect and hold you tight\n" +
                            "Cause you’re the only one who trusts me"
            );
            LessonEntity l13 = new LessonEntity(
                    "https://www.youtube.com/watch?v=pBTp2RWxq-s",
                    "[Vietsub+Lyrics] I Do - 911",
                    "My whole world changed from the moment I met you\n" +
                            "And it would never be the same\n" +
                            "Felt like I knew that I'd always love you\n" +
                            "From the moment I heard your name\n" +
                            "Everything was perfect, I knew this love is worth it\n" +
                            "Our own miracle in the makin"
            );
            LessonEntity l14 = new LessonEntity(
                    "https://www.youtube.com/watch?v=nQY4dIxY1H4",
                    "Chris Medina - What Are Words (Official Video)",
                    "Music video by Chris Medina performing What Are Words. (C) 2011 19 Recordings, Inc."
            );
            LessonEntity l15 = new LessonEntity(
                    "https://www.youtube.com/watch?v=btDd9rOlc2k",
                    "BIGBANG - MONSTER M/V",
                    "bukan monster. hanya seseorang yang diam-diam patah hati tanpa dia tau, aku pernah jatuh cinta."
            );
            LessonEntity l16 = new LessonEntity(
                    "https://www.youtube.com/watch?v=amOSaNX7KJg",
                    "숀 (SHAUN) - 웨이백홈 (Way Back Home) [Lyric Video]",
                    "For those who think English ver made this song popular.\n" +
                            "This song got popular before English version was released, incase you don't know in korean music shows and charts this song topped Bts and Blackpink. "
            );
            LessonEntity l17 = new LessonEntity(
                    "https://www.youtube.com/watch?v=h0UUqTCczHQ",
                    "lofi hip hop radio \uD83C\uDF31 beats to relax/study to",
                    "All pictures are collected by me or sent by fans, I just create more. So if there is any problem related to copyright, email me first and I will take care of it immediately!\n" +
                            "Thank you very much!!!"
            );
            LessonEntity l18 = new LessonEntity(
                    "https://www.youtube.com/watch?v=63nQdUoo388",
                    "Why so sad ? Lofi hip hop mix~ Stress Relief, Aesthetic Music",
                    "All pictures are collected by me or sent by fans, I just create more. So if there is any problem related to copyright, email me first and I will take care of it immediately!"
            );
            LessonEntity l19 = new LessonEntity(
                    "https://www.youtube.com/watch?v=Id6ARZyuxaw",
                    "Xe Đạp x Anh Yêu Em Nhiều Lắm - Nhạc Lofi Cực Chill Nhẹ Nhàng Thư Giãn 2021",
                    " Xe Đạp x Anh Yêu Em Nhiều Lắm\n" +
                            "Nhạc Lofi Cực Chill Nhẹ Nhàng Thư Giãn 2021\n" +
                            "More about Em Ơi"
            );
            LessonEntity l20 = new LessonEntity(
                    "https://www.youtube.com/watch?v=ArQHATe9zk0",
                    "Em Đi Xa Nơi Phương Trời Chỉ Có Mỗi Anh Nơi Này - Nhạc Lofi Chill 2021 - Mình Anh Nơi Này, Nàng Thơ",
                    "Em Đi Xa Nơi Phương Trời Chỉ Có Mỗi Anh Nơi Này" +
                            "Nhạc Lofi  Chill 2021 - Mình Anh Nơi Này, Nàng Thơ" +
                            " ore about Em Ơi"
            );

            lessonRepository.saveAll(
                    List.of(
                            l1, l2, l3, l4, l5,
                            l6, l7, l8, l9, l10,
                            l11, l12, l13, l14, l15,
                            l16, l17, l18, l19, l20
                    )
            );
        };
    }
}
