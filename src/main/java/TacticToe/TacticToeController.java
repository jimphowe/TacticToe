package TacticToe;

import TacticToe.game.entity.OnePlayerImpl;
import TacticToe.game.entity.LocationState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TacticToeController {

    OnePlayerImpl game = new OnePlayerImpl();
    public Boolean playerFirst;

    @GetMapping("/")
    public String home(Model model) {
        setModelGameAttributes(model, this);
        return "index";
    }

    @RequestMapping(value = "/restart")
    public String reset(Model model) {
        this.game.restart();
        if (!this.playerFirst) {
            game.computerMove();
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/undo")
    public String undo(Model model) {
        this.game.undo();
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/difficulty_selection")
    public String difficultySelection(Model model) {
        setModelGameAttributes(model, this.game);
        setModelGameAttributes(model, this);
        this.playerFirst = false;
        return "difficulty_selection";
    }

    @RequestMapping(value = "/level1")
    public String level1(Model model) {
        game = new OnePlayerImpl();
        this.game.setStartingBoard();
        this.game.difficulty = 1;
        this.game.firstPlayer = (playerFirst ? LocationState.RED : LocationState.GREEN);
        if (!this.playerFirst) {
            game.computerMove();
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/level2")
    public String level2(Model model) {
        game = new OnePlayerImpl();
        this.game.setStartingBoard();
        this.game.difficulty = 2;
        this.game.firstPlayer = (playerFirst ? LocationState.RED : LocationState.GREEN);
        if (!this.playerFirst) {
            game.computerMove();
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/level3")
    public String level3(Model model) {
        game = new OnePlayerImpl();
        this.game.setStartingBoard();
        this.game.difficulty = 3;
        this.game.firstPlayer = (playerFirst ? LocationState.RED : LocationState.GREEN);
        if (!this.playerFirst) {
            game.computerMove();
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/level4")
    public String level4(Model model) {
        game = new OnePlayerImpl();
        this.game.setStartingBoard();
        this.game.difficulty = 4;
        this.game.firstPlayer = (playerFirst ? LocationState.RED : LocationState.GREEN);
        if (!this.playerFirst) {
            game.computerMove();
        }
        setModelGameAttributes(model, this.game);
        return "game";
    }

    @RequestMapping(value = "/toggle_first_player")
    public String toggleFirstPlayer(Model model) {
        this.playerFirst = !this.playerFirst;
        setModelGameAttributes(model, this.game);
        setModelGameAttributes(model, this);
        return "difficulty_selection";
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

    private int numPieces(LocationState player) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (this.game.pieces[i][j][k] == player) {
                        counter += 1;
                    }
                }
            }
        }
        return counter;
    }

    // TODO this seems messy with all the gameOver checks
    // TODO BUG where strange behavior if click again before computer makes move
    public void makeMove(int x, int y, LocationState player) {
        try {
            if (game.isGameOver()) {
                return;
            }
            if (playerFirst && numPieces(LocationState.RED) > numPieces(LocationState.GREEN)) {
                return;
            }
            if (!playerFirst && numPieces(LocationState.RED) >= numPieces(LocationState.GREEN)) {
                return;
            }
            this.game.move(x,y,1,player);
            if (game.isGameOver()) {
                game.winner = "RED";
                return;
            }
            this.game.displayMessage = "";
            this.game.computerMove();
            if (game.isGameOver()) {
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

    private void setModelGameAttributes(Model model, OnePlayerImpl game) {
        model.addAttribute("game", game);
    }

    private void setModelGameAttributes(Model model, TacticToeController controller) {
        model.addAttribute("controller", controller);
    }
}