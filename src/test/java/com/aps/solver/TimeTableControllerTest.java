package com.aps.solver;

import com.aps.ApsApplication;
import com.aps.bean.Lesson;
import com.aps.bean.Room;
import com.aps.bean.TimeTable;
import com.aps.bean.Timeslot;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        "optaplanner.solver.termination.spent-limit=1h", // Effectively disable this termination in favor of the best-score-limit
        "optaplanner.solver.termination.best-score-limit=0hard/*soft"},classes = ApsApplication.class)
public class TimeTableControllerTest {

    private TimeTableSolver timeTableSolver = new TimeTableSolver();

    @Test
    public void solve() {
        TimeTable problem = generateProblem();
        TimeTable solution = timeTableSolver.solve(problem);
        System.out.println(new Date()+"----"+solution);
//        assertFalse(solution.getLessonList().isEmpty());
//        for (Lesson lesson : solution.getLessonList()) {
//            assertNotNull(lesson.getTimeslot());
//            assertNotNull(lesson.getRoom());
//        }
//        assertTrue(solution.getScore().isFeasible());
    }

    private TimeTable generateProblem() {
        List<Timeslot> timeslotList = new ArrayList<>();
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));

        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Room A"));
        roomList.add(new Room("Room B"));

        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(new Lesson(101L, "Math", "B. May", "9th grade"));
        lessonList.add(new Lesson(102L, "Physics", "M. Curie", "9th grade"));
        lessonList.add(new Lesson(103L, "Geography", "M. Polo", "10th grade"));
        lessonList.add(new Lesson(104L, "English", "I. Jones", "10th grade"));

        return new TimeTable(timeslotList, roomList, lessonList);
    }
}
