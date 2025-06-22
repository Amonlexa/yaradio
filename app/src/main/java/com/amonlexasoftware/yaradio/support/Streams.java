package com.amonlexasoftware.yaradio.support;

import com.amonlexasoftware.yaradio.R;
import com.amonlexasoftware.yaradio.models.RadioModel;

import java.util.List;

public class Streams {


    public static void getRadios(List<RadioModel> radioList) {

        radioList.add(new RadioModel(
                "Долгун радио (Сунтаар)",
                R.drawable.suntaar, "http://dolgunfm.ru:8000/dolgun",
                "104.0"));

        radioList.add(new RadioModel(
                "Радио «Тэтим - НВК Саха",
                R.drawable.sakha_flag,
                "https://icecast-saha.cdnvideo.ru/saha",
                "107.1"));

        radioList.add(new RadioModel(
                "Радио Виктория Саха",
                R.drawable.viktoria,"http://37.139.33.202:8000/stream.mp3",
                "104.5"));
    }





    public static String fm_tetim = "";
    public static String fm_tetim_image = "https://top-radio.ru/assets/image/radio/180/tatimfm.png";

    public static String fv_dolgun = "";
    public static String fv_dolgun_image = "https://sun9-68.userapi.com/c10180/g10624072/a_bc4d57ee.jpg";

    public static String viktoria = "http://37.139.33.202:8000/stream.mp3";
    public static String viktoria_image = "https://top-radio.ru/assets/image/radio/180/radio-sahalii-viktoriya.png";

}
