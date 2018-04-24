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
    public @ResponseBody String showIndex()
    {
        //Check the database as soon as you run this route. This should be the first thing that you do.
        return artistes.toString() ;
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
    * Add these songs
    *
     *  Off The Wall - Michael Jackson
     *  Purple Rain - Prince
     *  I'll be watching you - String
     *  Use the IDs in /addsong
     *  Use the dropdown in /addsongiwthform
     *  */

    @RequestMapping("/addsong")
    public String addSong(Model model)
    {
        model.addAttribute("theSong",new Song());
        return "addsong";
    }


    @RequestMapping("/savesong")
    public @ResponseBody  String saveSong(@ModelAttribute("theSong") Song song, BindingResult result)
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
