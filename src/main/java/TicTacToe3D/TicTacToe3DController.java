package TicTacToe3D;

import TicTacToe3D.game.entity.Game;
import TicTacToe3D.game.entity.LocationState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicTacToe3DController {

    Game game = new Game();

    @RequestMapping(value = "/game")
    public String reset(Model model) {
        game = new Game();
        this.game.setStartingBoard();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_left")
    public String rotateLeft(Model model) {
        this.game.rotateLeft();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_right")
    public String rotateRight(Model model) {
        this.game.rotateRight();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_up")
    public String rotateUp(Model model) {
        this.game.rotateUp();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/rotate_down")
    public String rotateDown(Model model) {
        this.game.rotateDown();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    // TODO this seems messy with all the gameOver checks
    public void makeMove(int x, int y, LocationState player) {
        try {
            if (game.gameOver()) {
                return;
            }
            this.game.move(x,y,player);
            if (game.gameOver()) {
                game.winner = "RED";
                return;
            }
            this.game.displayMessage = "";
            this.game.computerMove();
            if (game.gameOver()) {
                game.winner = "GREEN";
            }
        }
        catch (IllegalArgumentException ex) {
            this.game.displayMessage = "The spot chosen must either be empty or be able to push other " +
                    "balls forward without pushing one out";
        }
    }

    @RequestMapping(value = "/top_left")
    public String topLeft(Model model) {
        this.makeMove(0,0,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/top_middle")
    public String topMiddle(Model model) {
        this.makeMove(1,0,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/top_right")
    public String topRight(Model model) {
        this.makeMove(2,0,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/middle_left")
    public String middleLeft(Model model) {
        this.makeMove(0,1,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/middle_middle")
    public String middleMiddle(Model model) {
        this.makeMove(1,1,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/middle_right")
    public String middleRight(Model model) {
        this.makeMove(2,1,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/bottom_left")
    public String bottomLeft(Model model) {
        this.makeMove(0,2,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/bottom_middle")
    public String bottomMiddle(Model model) {
        this.makeMove(1,2,LocationState.RED);
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/bottom_right")
    public String bottomRight(Model model) {
        this.makeMove(2,2,LocationState.RED);
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
