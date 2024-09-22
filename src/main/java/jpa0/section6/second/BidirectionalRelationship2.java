package jpa0.section6.second;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa0.section6.Fellow;
import jpa0.section6.Team;

import java.util.List;

/**
 * 2. 양방향 연관관계
 * 양방향 연관관계의 주인 `N`만 등록과 수정을 한다.
 * 양방향 연관관계에서 `1`은 조회만 가능하다.
 * 순수 객체 관점 : `N`쪽과 `1`쪽 [양쪽] 전부 등록, 수정 코드 [값]를 넣어주는 게 맞다.
 * 연관관계 편의 메서드 : `N`에 넣을 때를 기준으로 하면, `N`에 넣을 때 `1`에도 값을 넣어주게 메서드 로직을 짠다.
 * 양방향 맵핑 주의 : 무한 루프를 조심하자 [toString(), lombok, JSON 라이브러리]
 * 무한 루프 :`동료`가 `팀`을 `toString()`하고, `팀`은 `동료들`에 있는 `동료`를 `toString()`하고, 다시 `동료`는 `팀`을 `toString()`하고...
 */
public class BidirectionalRelationship2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setTeamname("섹션6-3, 양방향 연관관계");

            em.persist(team);

            Fellow fellow = new Fellow();
            fellow.setFellowname("섹션6-3, 양방향 연관관계");
            //연관관계 편의 메서드 : `fellow`에는 `team`을 넣고, `team`의 `fellows`에는 `fellow`를 넣어주는 메서드
            fellow.changeTeam(team);

            em.persist(fellow);

            //영속성 컨텍스트에 있는 데이터를 가져온다.
            Team findTeam = em.find(Team.class, team.getId());
            List<Fellow> fellows = findTeam.getFellows();


            for (Fellow f : fellows) {
                System.out.println("동료 이름 = " + f.getFellowname());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
