package io.techlab.service;

import io.techlab.dao.UserDao;
import io.techlab.data.request.JotcRequest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is created for testing JOTCService class.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class JOTCServiceTest {

  @InjectMocks
  private JOTCService jotcService;
  @Mock
  private UserDao userDao;

  @Test
  public void testSolveJotcProblem() {
    final JotcRequest jotcRequest = new JotcRequest();
    jotcRequest.setEmail("orhanpolat@gmail.com");
    jotcRequest.setFullName("Orhan Polat");
    jotcRequest.setJotcArray(prepareInputArray());
    int actualResult = jotcService.solveJotcProblem(jotcRequest);
    assertThat(actualResult).isEqualTo(3);
  }

  private List<Integer> prepareInputArray() {
    List<Integer> arrayList = new ArrayList<>();
    arrayList.add(0);
    arrayList.add(1);
    arrayList.add(0);
    arrayList.add(0);
    arrayList.add(1);
    arrayList.add(0);
    return arrayList;
  }
}