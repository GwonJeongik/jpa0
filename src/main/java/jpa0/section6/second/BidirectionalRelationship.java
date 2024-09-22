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
 * 객체는 `참조`를 통해서 연관관계를 맺는다.
 * 테이블은 `외래 키`를 통해서 연관관계를 맺는다.
 * <p>
 * 객체 기준 : 단방향 연관관계가 2개 설정 된 거다.
 * `동료` -> `팀`
 * `팀` -> `동료`
 * 테이블 기준 : 외래 키 하나로, 두 테이블의 연관관계를 맺는다.
 * <p>
 * 연관관계의 주인은 `외래 키`를 가진쪽이다.
 * `N`쪽이 주인이다. = ManyToOne
 * `주인`만이 `등록`, `수정`이 가능하다.
 * `1`쪽이 주인이 아니다. = OneToMany(mappedBy = "team")
 * `mappedBy`는 주인이 아니다. -> 읽기만 가능하다.
 */
public class BidirectionalRelationship {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setTeamname("섹션6-2 양방향 연관관계 팀B");

            System.out.println("== 기본키 생성 전략 IDENTITY 사용 시점 팀 인서트 쿼리 시작 ==");
            em.persist(team);
            System.out.println("== 기본키 생성 전략 IDENTITY 사용 시점 팀 인서트 쿼리 종료 ==");

            Fellow fellowA = new Fellow();
            fellowA.setFellowname("섹션6-2, 양방향 연관관계 동료A");
            fellowA.setTeam(team);

            Fellow fellowB = new Fellow();
            fellowB.setFellowname("섹션6-2, 양방향 연관관계 동료B");
            fellowB.setTeam(team);

            System.out.println("== 기본키 생성 전략 IDENTITY 사용 시점 동료 인서트 쿼리 시작 ==");
            em.persist(fellowA);
            em.persist(fellowB);
            System.out.println("== 기본키 생성 전략 IDENTITY 사용 시점 동료 인서트 쿼리 종료 ==");

            em.clear();

            System.out.println("== team 조회 인서트 쿼리문 시점 시작 ==");
            Team findTeam = em.find(Team.class, team.getId());
            System.out.println("== team 조회 인서트 쿼리문 시점 종료 ==");
            List<Fellow> fellows = findTeam.getFellows();

            System.out.println("== fellow 조회 인서트 쿼리문 [지연 로딩] 시점 시작 ==");
            for (Fellow f : fellows) {
                System.out.println("동료 이름 : " + f.getFellowname());
            }
            System.out.println("== fellow 조회 인서트 쿼리문 [지연 로딩] 시점 종료 ==");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
