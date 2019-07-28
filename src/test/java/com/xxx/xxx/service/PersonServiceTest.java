package com.xxx.xxx.service;

import com.xxx.xxx.domain.Person;
import com.xxx.xxx.domain.PersonRequest;
import com.xxx.xxx.utils.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.doNothing;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TimeUnit.class)
@SuppressStaticInitializationFor("com.xxx.xxx.utils.SalaryCalculator")
public class PersonServiceTest {
    @InjectMocks
    private PersonService personService;

    @Test
    public void should_find_person() {
        //given
        String name = "name";
        PersonRequest request = new PersonRequest(name);

        //when
        Person result = personService.find(request);
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(
                new Person("None", "None", BigDecimal.ZERO));
    }

    @Test
    public void should_find_person_when_name_is_james() throws Exception {
        //given
        String name = "James";
        PersonRequest request = new PersonRequest(name);
        PowerMockito.mockStatic(TimeUnit.class);
        doNothing().when(TimeUnit.class, "sleep", anyLong());
        //when
        Person result = personService.find(request);
        //then
        assertThat(result).isEqualToComparingFieldByFieldRecursively(
                new Person("Merson", "James", BigDecimal.TEN.add(BigDecimal.TEN)));
    }
}