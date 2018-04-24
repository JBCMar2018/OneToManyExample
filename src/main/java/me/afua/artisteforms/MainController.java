package me.afua.artisteforms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    ArtisteRepository artistes;

    @Autowired
    SongRepository songs;

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        //Check the database as soon as you run this route. This should be the first thing that you do.
        model.addAttribute("allartistes",artistes.findAll());
        return "index";
    }

    @RequestMapping("/addartistes")
    public @ResponseBody String addArtistes()
    {
        //This prepopulates the database.
        Artiste a = new Artiste("Michael Joseph Jackson");
        artistes.save(a);

        a = new Artiste("Prince Rogers Nelson");

        artistes.save(a);
        a = new Artiste("Gordon Matthew Thomas Sumner");

        a.setStageName("Sting");
        artistes.save(a);
        return artistes.findAll().toString();
    }

    /*
       *Need test data? Add these songs
     *
     *  Off The Wall, Beat It, Thriller - Michael Jackson
     *  Purple Rain, When Doves Cry - Prince
     *  I'll be watching you, Englishman In New York - String
     *  Use the IDs in /addsong
     *  Use the dropdown in /addsongiwithform
     *  */

    @RequestMapping("/addsong")
    public String addSong(Model model)
    {
        model.addAttribute("theSong",new Song());
        return "addsong";
    }


    @RequestMapping("/savesong")
    public String saveSong(@ModelAttribute("theSong") Song song, BindingResult result)
    {
        songs.save(song);
        return "redirect:/";
    }

    @RequestMapping("/addsongwithform")
    public String addSongWithDropDown(Model model)
    {
        model.addAttribute("theSong",new Song());
        model.addAttribute("allartistes",artistes.findAll());
        return "addsongform";
    }
}
