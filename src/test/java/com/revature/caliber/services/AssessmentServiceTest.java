package com.revature.caliber.services;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.repositories.AssessmentRepository;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AssessmentServiceTest {

	AssessmentRepository assessmentRepository = mock(AssessmentRepository.class);

	List<Assessment> assessmentsList = new ArrayList<>();
	List<Assessment> batchAssessmentsList = new ArrayList<>();
	List<Assessment> categoryAssessmentsList = new ArrayList<>();
	List<Assessment> batchAndWeekNumAssessmentsList = new ArrayList<>();

	@Before
	public void setup() {
		// AssessmentService tests setup
		Assessment a1 = new Assessment(12, 74, "a1", "java", 2, 84, 1);

		Assessment a2 = new Assessment(17, 20, "a2", "sales force", 7, 31, 2);

		Assessment a3 = new Assessment(20, 97, "a3", "big data", 5, 9, 3);

		Assessment a4 = new Assessment(22, 87, "a4", "java", 2, 84, 9);

		assessmentsList.add(a1);
		assessmentsList.add(a2);
		assessmentsList.add(a3);
		assessmentsList.add(a4);

		batchAssessmentsList.add(a1);
		batchAssessmentsList.add(a4);

		categoryAssessmentsList.add(a4);

		batchAndWeekNumAssessmentsList.add(a1);
		batchAndWeekNumAssessmentsList.add(a4);

		when(assessmentRepository.findAssessmentsByBatchId(84)).thenReturn(batchAssessmentsList);

		when(assessmentRepository.findAssessmentsByBatchId(847932)).thenReturn(null);

		when(assessmentRepository.findAssessmentsByBatchId((int) 'c')).thenReturn(null);

		when(assessmentRepository.findAssessmentsByAssessmentCategory(9)).thenReturn(categoryAssessmentsList);

		when(assessmentRepository.findAssessmentsByBatchIdAndWeekNumber(84, 2))
				.thenReturn(batchAndWeekNumAssessmentsList);

	}

	@Test
	public void testFindAssessmentsByBatchIdWithExpectedInput() {
		assertThat(assessmentRepository.findAssessmentsByBatchId(84), hasItems(
				new Assessment(12, 74, "a1", "java", 2, 84, 1), new Assessment(22, 87, "a4", "java", 2, 84, 9)));
	}

	@Test
	public void testFindAssessmentsByBatchIdWithNonExistantId() {
		assertEquals(null, assessmentRepository.findAssessmentsByBatchId(847932));
	}

	@Test
	public void testFindAssessmentsByBatchIdWithInvalidInput() {
		assertEquals(null, assessmentRepository.findAssessmentsByBatchId((int) 'c'));
	}

	@Test
	public void testFindAssessmentsByCategoryWithExpectedInput() {
		assertThat(assessmentRepository.findAssessmentsByAssessmentCategory(9),
				hasItems(new Assessment(22, 87, "a4", "java", 2, 84, 9)));
	}

	@Test
	public void testFindAssessmentsByCategoryWithNonExistantId() {
		List<Assessment> emptyList = new ArrayList<>();
		assertEquals(emptyList, assessmentRepository.findAssessmentsByBatchId(29384));
	}

	@Test
	public void testFindAssessmentsByBatchIdAndWeekNumberWithExpectedInputs() {
		assertThat(assessmentRepository.findAssessmentsByBatchIdAndWeekNumber(84, 2), hasItems(
				new Assessment(12, 74, "a1", "java", 2, 84, 1), new Assessment(22, 87, "a4", "java", 2, 84, 9)));
	}

	@Test
	public void testFindAssessmentsByBatchIdAndWeekNumberWithNonExistantInputs() {
		List<Assessment> emptyList = new ArrayList<>();
		assertEquals(emptyList, assessmentRepository.findAssessmentsByBatchIdAndWeekNumber(3471023, 89745816));
	}

}
