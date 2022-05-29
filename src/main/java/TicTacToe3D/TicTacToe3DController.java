package TicTacToe3D;

import TicTacToe3D.game.entity.Game;
import TicTacToe3D.game.entity.Piece;
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

    @RequestMapping(value = "/top_left")
    public String topLeft(Model model) {
        try {
            this.game.move(0,0, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/top_middle")
    public String topMiddle(Model model) {
        try {
            this.game.move(1,0, Piece.RED);
        }
        catch (IllegalArgumentException e) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/top_right")
    public String topRight(Model model) {
        try {
            this.game.move(2, 0, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/middle_left")
    public String middleLeft(Model model) {
        try {
            this.game.move(0, 1, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/middle_middle")
    public String middleMiddle(Model model) {
        try {
            this.game.move(1, 1, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/middle_right")
    public String middleRight(Model model) {
        try {
            this.game.move(2, 1, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/bottom_left")
    public String bottomLeft(Model model) {
        try {
            this.game.move(0, 2, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/bottom_middle")
    public String bottomMiddle(Model model) {
        try {
            this.game.move(1, 2, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/bottom_right")
    public String bottomRight(Model model) {
        try {
            this.game.move(2, 2, Piece.RED);
        }
        catch (IllegalArgumentException ex) {
            ;
        }
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
