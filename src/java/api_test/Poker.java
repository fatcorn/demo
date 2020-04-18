package api_test;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 扑克牌与随机洗牌
 * @author fatKarin
 * @date 2020/4/7 11:25
 */
public class Poker {
    
    private List<Card> cards = new ArrayList<>(54);
    
    private static String[] names = {"A","2","3","4","5","6","7","8","9","10","J","Q","K","JOKER1","JOKER2"};

    public static void main(String[] args) {
        Poker poker = new Poker();

        poker.createCard(poker.cards);

        poker.washCards(poker.cards);

        poker.cards.forEach(x -> System.out.println(x.getName() + "\t" + x.getType()));
    }


    private void createCard(List<Card> cards){
        if(cards == null) {
            System.out.println("错误的参数");
            return;
        }
        for (String name : names) {
            for (TypeEnum typeEnum : TypeEnum.values()) {
                if (name.equals("JOKER1") || name.equals("JOKER2")) {
                    Card card = new Card();
                    card.setName(name);
                    cards.add(card);
                    break;
                }
                Card card = new Card();
                card.setName(name);
                card.setType(typeEnum);
                cards.add(card);
            }
        }
    }

    private void washCards(List<Card> cards) {
        Collections.shuffle(cards);
    }
}

@Data
@NoArgsConstructor
class Card {
    private TypeEnum type;

    private String name;

}

enum TypeEnum {
    SPADE,
    HEART,
    CLUB,
    DIAMOND
}



