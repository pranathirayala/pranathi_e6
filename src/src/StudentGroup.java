import java.util.Date;
import java.lang.Exception;
import java.util.*;
import java.util.ArrayList;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * <p>
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 */
public class StudentGroup implements StudentArrayOperation {

    private Student[] students;
    private static int noOfStudents = 0;

    /**
     * DO NOT remove or change this constructor, it will be used during task check
     *
     * @param length
     */
    public StudentGroup(int length) {
        this.students = new Student[length];
    }


    @Override
    public Student[] getStudents() {
        // Add your implementation here
        return students;
    }

    @Override
    public void setStudents(Student[] students) {
        // Add your implementation here
        if (students == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < students.length; i++)
            this.students[i] = students[i];

    }

    @Override
    public Student getStudent(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        else
            return students[index];
    }

    @Override
    public void setStudent(Student student, int index) {
        // Add your implementation here
        if (student == null || index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        else
            students[index] = student;

    }

    @Override
    public void addFirst(Student student) {
        // Add your implementation here

        if (student == null || students.length == noOfStudents)
            throw new IllegalArgumentException();
        for (int i = noOfStudents; i > 0; i--) {
            students[i] = students[i-1];
        }
        students[0] = student;
        noOfStudents++;
    }

    @Override
    public void addLast(Student student) {
        // Add your implementation here
        if (student == null || students.length == noOfStudents)
            throw new IllegalArgumentException();
        students[noOfStudents] = student;
        noOfStudents++;

    }

    @Override
    public void add(Student student, int index) {
        // Add your implementation here
        if (student == null || index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        students[index] = student;
        noOfStudents++;
    }

    @Override
    public void remove(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        for(int i=index; i < noOfStudents ;i++) {
            students[i] = students[i+1];
        }
        students[noOfStudents] = null;
        noOfStudents--;
    }

    @Override
    public void remove(Student student) {
        // Add your implementation here
        if (student == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < noOfStudents; i++) {
            if (students[i].equals(student)) {
                remove(i);
                break;
            }
        }
    }

    @Override
    public void removeFromIndex(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        else {
            for (int i = index; i < noOfStudents; i++) {
                students[i] = null;
                noOfStudents--;
            }
        }

    }

    @Override
    public void removeFromElement(Student student) {
        // Add your implementation here
        int pos = 0;
        if (student == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < noOfStudents; i++) {
            if (students[i].equals(student)) {
                removeFromIndex(i);
                break;
            }
        }
    }

    @Override
    public void removeToIndex(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        for (int i = 0; i <= index; i++) {
            remove(i);
        }
    }

    @Override
    public void removeToElement(Student student) {
        // Add your implementation here
        int pos = 0;
        if (student == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < noOfStudents; i++) {
            if (students[i].equals(student)) {
                removeToIndex(i);
            }
        }
    }

    @Override
    public void bubbleSort() {
        // Add your implementation here
        int n = students.length;
        Student temp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (students[j - 1].id > students[j].id) {
                    temp = students[j - 1].id;
                    students[j - 1].id = students[j].id;
                    students[j].id = temp;
                }
            }
        }
    }

    @Override
    public Student[] getByBirthDate(Date date) {
        // Add your implementation here
        Student[] st3 = new Student[students.length];
        if (date == null)
            throw new IllegalArgumentException();
        int j = 0;
        for (int i = 0; i < noOfStudents; i++) {
            if (students[i].getBirthDate().equals(date)) {
                st3[j] = students[i];
                j++;
            }
        }
        return st3;
    }


    @Override
    public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
        // Add your implementation here
        Student[] st1;
        if (firstDate == null || lastDate == null)
            throw new IllegalArgumentException();
        else {
            for (int i = 0; i < students.length; i++) {
                if (((students[i].getBirthDate()) >= firstDate) ||((students[i].getBirthDate()) <= lastDate))
                st1.set(students[i]);
            }
        }
        return st1;

    }

    @Override
    public Student[] getNearBirthDate(Date date, int days) {
        // Add your implementation here
        Student[] st2 = new Student[students.length];
        if (date.equals(null))
            throw new IllegalArgumentException();
        else {
            for (int i = 0; i < students.length; i++) {
                if(((students[i].getBirthDate()).equals(date)) || ((students[i].getBirthDate()).equals(date.getDay() + days)))
                    st2.add(students[i]);
            }

        }
        return st2;
    }


    @Override
    public int getCurrentAgeByDate(int indexOfStudent) {
        // Add your implementation here
        int age;
        if (indexOfStudent == 0)
            throw new IllegalArgumentException();
        else {
            Calendar t = Calendar.getInstance();
            int cy = t.get(Calendar.YEAR);
            int dy = students[indexOfStudent].getBirthDate().get(Calendar.YEAR);
            age = cy - dy;
            int cm = t.get(Calendar.MONTH);
            int dm = students[indexOfStudent].getBirthDate().get(Calendar.MONTH);
            if (dm > cm)
                age--;
            else if (dm == cm) {
                int cd = t.get(Calendar.DAY_OF_MONTH);
                int dd = students[indexOfStudent].getBirthDate().get(Calendar.DAY_OF_MONTH);
                if (dd > cd)
                    age--;
            }
        }
        return age;
    }

    @Override
    public Student[] getStudentsByAge(int age) {
        // Add your implementation here
        Student[] st;
        for (int i = 0; i < students.length; i++) {
            int reqage = getCurrentAgeByDate(i);
            if (reqage == age)
                st.add(students[i]);
            return st;
        }
    }

    @Override
    public Student[] getStudentsWithMaxAvgMark() {
        // Add your implementation here
        Student[] temp = new Student[students.length];
        Student[] st;
        for (int i = 0; i < temp.length; i++) {
            int index = i;
            for (int j = i + 1; j < temp.length; j++) {
                if (temp[j].compareTo(temp[index]) == 1) {
                    index = j;
                }
            }
            Student small = temp[index];
            temp[index] = temp[i];
            temp[i] = small;
        }
        for (int i = 0; i < temp.length; i++) {
            if ((temp[temp.length - 1]).getAvgMark() == temp[i].getAvgMark())
                st.add(temp[i]);
        }
        return st;

    }

    @Override
    public Student getNextStudent(Student student) {
        // Add your implementation here
        Student[] st8;
        if (student == null)
            throw new IllegalArgumentException();
        else {
            for (int i = 0; i < students.length; i++) {
                if (students[i].getId() == student.getId()) {
                    student = students[i + 1];
                    return student;
                }
            }

        }

    }
}
