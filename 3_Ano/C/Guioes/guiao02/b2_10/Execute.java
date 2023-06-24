import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("CheckReturnValue")
public class Execute extends QuizBaseVisitor<String> {

   private Map<String, List<Quiz>> quizzes = new HashMap<>();

   @Override
   public String visitProgram(QuizParser.ProgramContext ctx) {
      Iterator<QuizParser.QuizContext> it = ctx.quiz().iterator();
      List<List<String>> quizListTxt = new ArrayList<>();
      while (it.hasNext()) {
         String quiz = visit(it.next());
         quizListTxt.add(Arrays.asList(quiz.split("lkjhgfdsa\n")));
      }
      System.out.println(quizListTxt);
      // for each quiz
      for (int i = 0; i < quizListTxt.size(); i++) {
         Question question = null;
         List<Answer> answers = new ArrayList<>();
         // for each string in quiz
         for (int j = 0; j < quizListTxt.get(i).size(); j++) {
            String s = quizListTxt.get(i).get(j);
            if (j == 0) {
               int firstQuote = s.indexOf('"');
               String id = s.substring(0, firstQuote);
               String txt = s.substring(firstQuote + 1, s.length() - 1);
               question = new Question(id, txt);
            } else {
               System.out.println(s);
               int firstQuote = s.indexOf('"');
               int secondQuote = s.indexOf('"', firstQuote + 1);
               int semicolon = s.indexOf(';');
               String txt = s.substring(firstQuote + 1, secondQuote);
               int cotation = Integer.parseInt(s.substring(secondQuote + 2, semicolon));
               answers.add(new Answer(txt, cotation));
            }
         }
         String key = question.getIdentifier();
         Quiz q = new Quiz(question, answers);
         quizzes.computeIfAbsent(key, k -> new ArrayList<>()).add(q);
      }
      System.out.println(quizzes);
      return null;
   }

   public Map<String, List<Quiz>> getQuizzes() {
      return quizzes;
   }

   @Override
   public String visitQuiz(QuizParser.QuizContext ctx) {
      String question = visit(ctx.quizHeader());
      StringBuilder res = new StringBuilder(question + "lkjhgfdsa\n");
      Iterator<QuizParser.AnswerContext> it = ctx.answer().iterator();
      while (it.hasNext()) {
         res.append(visit(it.next()) + ";lkjhgfdsa\n");
      }
      return res.toString();
   }

   @Override
   public String visitQuizHeader(QuizParser.QuizHeaderContext ctx) {
      return ctx.QUESTION_ID().getText() + ctx.QUOTED_STRING().getText();
   }

   @Override
   public String visitAnswer(QuizParser.AnswerContext ctx) {
      return ctx.QUOTED_STRING().getText() + ":" + ctx.INTEGER().getText();
   }
}
