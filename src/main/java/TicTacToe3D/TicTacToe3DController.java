package TicTacToe3D;

import TicTacToe3D.game.entity.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicTacToe3DController {

    Game game = new Game();

    @RequestMapping(value = "/game")
    public String reset(Model model) {
        this.game.setBoard();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_left")
    public String rotateLeft(Model model) {
        this.game.board.rotateLeft();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_right")
    public String rotateRight(Model model) {
        this.game.board.rotateRight();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_up")
    public String rotateUp(Model model) {
        this.game.board.rotateUp();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_down")
    public String rotateDown(Model model) {
        this.game.board.rotateDown();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    private void setModelGameAttributes(Model model, Game game) {
        model.addAttribute("game", game);
    }

}
