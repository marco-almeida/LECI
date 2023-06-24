import java.util.List;

public class Quiz {
    private Question question;
    private List<Answer> answers;

    public Quiz(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

}
