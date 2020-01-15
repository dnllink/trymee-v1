/**
 * 
 */
package br.com.bonaldoapps.trymee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.bonaldoapps.trymee.dao.CandidateDAO;
import br.com.bonaldoapps.trymee.dao.CategoryDAO;
import br.com.bonaldoapps.trymee.dao.LevelDAO;
import br.com.bonaldoapps.trymee.dao.ProcessDAO;
import br.com.bonaldoapps.trymee.dao.QuestionDAO;
import br.com.bonaldoapps.trymee.dao.TestDAO;
import br.com.bonaldoapps.trymee.entity.Alternative;
import br.com.bonaldoapps.trymee.entity.Candidate;
import br.com.bonaldoapps.trymee.entity.Process;
import br.com.bonaldoapps.trymee.entity.Question;
import br.com.bonaldoapps.trymee.entity.Test;
import br.com.bonaldoapps.trymee.entity.User;
import br.com.bonaldoapps.trymee.entity.enums.QuestionTypeEnum;

/**
 * @author Daniel
 *
 */
@Stateless
public class TutorialService {

	private static final String QUESTION_1_DESCRIPTION = "Qual é o trecho de HTML correto para criar um link que será aberto em uma nova janela?";

	private static final String ALTERNATIVE_QS1_1_DS = "<a href=\"http://www.google.com\" target=\"_blank\">Link</a>";

	private static final String ALTERNATIVE_QS1_2_DS = "<a href=\"http://www.google.com\" target=\"_self\">Link</a>";

	private static final String ALTERNATIVE_QS1_3_DS = "<a url=\"http://www.google.com\" target=\"_blank\">Link</a>";

	private static final String ALTERNATIVE_QS1_4_DS = "<a src=\"http://www.google.com\" target=\"_blank\">Link</a>";

	private static String[] alts1 = { ALTERNATIVE_QS1_1_DS, ALTERNATIVE_QS1_2_DS, ALTERNATIVE_QS1_3_DS,
			ALTERNATIVE_QS1_4_DS };

	private static final String QUESTION_2_DESCRIPTION = "Um arquivo Javascript (.js) externo importado no HTML deve conter a tag <script>?";

	private static final String ALTERNATIVE_QS2_1_DS = "Sim";

	private static final String ALTERNATIVE_QS2_2_DS = "Não";

	private static String[] alts2 = { ALTERNATIVE_QS2_1_DS, ALTERNATIVE_QS2_2_DS };

	private static final String QUESTION_3_DESCRIPTION = "O seletor da biblioteca Jquery $(\"div\") seleciona:";

	private static final String ALTERNATIVE_QS3_1_DS = "O primeiro elemento <div> encontrado";

	private static final String ALTERNATIVE_QS3_2_DS = "O primeiro elemento com a classe \"div\"";

	private static final String ALTERNATIVE_QS3_3_DS = "Todos os elementos do tipo <div>";

	private static final String ALTERNATIVE_QS3_4_DS = "Todos os elementos com o atributo id igual a \"div\"";

	private static String[] alts3 = { ALTERNATIVE_QS3_1_DS, ALTERNATIVE_QS3_2_DS, ALTERNATIVE_QS3_3_DS,
			ALTERNATIVE_QS3_4_DS };

	private static final String QUESTION_4_DESCRIPTION = "Qual comando SQL é usado para retornar apenas valores diferentes em uma query?";

	private static final String ALTERNATIVE_QS4_1_DS = "DIFFERENT";

	private static final String ALTERNATIVE_QS4_2_DS = "UNIQUE";

	private static final String ALTERNATIVE_QS4_3_DS = "DISTINCT";

	private static final String ALTERNATIVE_QS4_4_DS = "INDEX";

	private static String[] alts4 = { ALTERNATIVE_QS4_1_DS, ALTERNATIVE_QS4_2_DS, ALTERNATIVE_QS4_3_DS,
			ALTERNATIVE_QS4_4_DS };

	private static final String QUESTION_5_DESCRIPTION = "Dado o seguinte trecho de código Java:  public class Calculator {   int num = 100;   public void calc(int num) {     this.num = num * 10;   }   public void printNum(){     System.out.println(num);   }   public static void main(String[] args) {     Calculator obj = new Calculator ();     obj.calc(2);     obj.printNum();   } } Qual o resultado exibido?";

	private static final String ALTERNATIVE_QS5_1_DS = "20";

	private static final String ALTERNATIVE_QS5_2_DS = "100";

	private static final String ALTERNATIVE_QS5_3_DS = "1000";

	private static final String ALTERNATIVE_QS5_4_DS = "22";

	private static final String ALTERNATIVE_QS5_5_DS = "2";

	private static final String ALTERNATIVE_QS5_6_DS = "18";

	private static String[] alts5 = { ALTERNATIVE_QS5_1_DS, ALTERNATIVE_QS5_2_DS, ALTERNATIVE_QS5_3_DS,
			ALTERNATIVE_QS5_4_DS, ALTERNATIVE_QS5_5_DS, ALTERNATIVE_QS5_6_DS };

	private static String[] questions = { QUESTION_1_DESCRIPTION, QUESTION_2_DESCRIPTION, QUESTION_3_DESCRIPTION,
			QUESTION_4_DESCRIPTION, QUESTION_5_DESCRIPTION };

	private static final Long LEVEL_JR_ID = 1l;

	private static final String CATEGORY_JAVA_CODE = "JAVA";

	private static final String CATEGORY_HTML_CODE = "HTML";

	private static final String CATEGORY_JS_CODE = "JS";

	private static final String CATEGORY_JQUERY_CODE = "JQUERY";

	private static final String CATEGORY_SQL_CODE = "SQL";

	private static String[] categories = { CATEGORY_HTML_CODE, CATEGORY_JS_CODE, CATEGORY_JQUERY_CODE,
			CATEGORY_SQL_CODE, CATEGORY_JAVA_CODE };

	private static final String TEST_DS = "Prova básica para desenvolvedor Java Web Junior";

	private static final String TEST_NAME = "Java Web JR";

	private static final String PROCESS_DS = "Desenvolvedor Java Web JR";

	@Inject
	private QuestionDAO questionDAO;

	@Inject
	private TestDAO testDAO;

	@Inject
	private ProcessDAO processDAO;

	@Inject
	private CandidateDAO candidateDAO;

	@Inject
	private LevelDAO levelDAO;

	@Inject
	private CategoryDAO categoryDAO;

	public Process createTutorial(User user) {

		// create candidate
		Candidate c = new Candidate();

		c.setEmail(user.getEmail());
		c.setName(user.getName());
		c.setCreationDate(new Date());
		c.setUser(user);

		candidateDAO.save(c);

		List<Question> questionList = new ArrayList<>();

		for (int i = 0; i < questions.length; i++) {

			Question q1 = new Question();

			q1.setDescription(questions[i]);
			q1.setLevel(levelDAO.find(LEVEL_JR_ID));
			q1.setCategory(categoryDAO.searchByCode(categories[i]));
			q1.setType(QuestionTypeEnum.MULTIPLA_ESCOLHA);
			q1.setUser(user);

			String[] alternatives;
			Integer correctIndex;

			switch (i) {
			case 0:
				alternatives = alts1;
				correctIndex = 0;
				break;
			case 1:
				alternatives = alts2;
				correctIndex = 1;
				break;
			case 2:
				alternatives = alts3;
				correctIndex = 2;
				break;
			case 3:
				alternatives = alts4;
				correctIndex = 2;
				break;
			case 4:
				alternatives = alts5;
				correctIndex = 0;
				break;
			default:
				alternatives = alts1;
				correctIndex = 0;
				break;
			}

			for (int i1 = 0; i1 < alternatives.length; i1++) {

				Alternative a1 = new Alternative();
				a1.setDescription(alternatives[i1]);
				a1.setCorrect(i1 == correctIndex);
				a1.setQuestion(q1);

				q1.addAlternative(a1);

			}

			questionDAO.save(q1);
			questionList.add(q1);

		}

		// create test
		Test t = new Test();

		t.setUser(user);
		t.setDescription(TEST_DS);
		t.setLevel(levelDAO.find(LEVEL_JR_ID));
		t.setName(TEST_NAME);
		t.addCategory(categoryDAO.searchByCode(CATEGORY_JAVA_CODE));

		for (Question q : questionList) {
			t.addQuestion(q);
		}

		testDAO.save(t);

		// create process
		Process p = new Process();
		p.setDescription(PROCESS_DS);
		p.setStartDate(new Date());
		p.setTest(t);
		p.addCandidate(c);
		p.setUser(user);

		processDAO.save(p);

		Process exampleProcess = processDAO.listWithParams(null, null, null, PROCESS_DS, user.getId(), null, null)
				.get(0);

		return exampleProcess;

	}

}
