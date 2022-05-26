package TicTacToe3D;

import TicTacToe3D.game.entity.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class TicTacToe3DController {

    //@GetMapping("/")
    //public String index() {
    //    return "Greetings from Spring Boooo!" + '\n';
    //}

    @RequestMapping(value = "/index")
    public String index(Model model) {

        Game game = new Game();
        game.setBoard("x x x\nx x x\nx x x");

        setModelGameAttributes(model, game);

        return "index";
    }

    @GetMapping("/game")
    public String play() {

        return "game";
    }

    private void setModelGameAttributes(Model model, Game game) {
        model.addAttribute("board", game.getBoard());
    }

}
