/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.models.UserInformation;

import java.util.List;

/**
 * @author nazar
 */
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    UserInformation findFirstByUser_Id(Long id);

    UserInformation findFirstByNameAndSurnameAndUser_Role(String name, String surname, Role userRole);

    List<UserInformation> findAllByNameStartingWithAndUser_Role(String string, Role userRole);

    List<UserInformation> findAllByUser_Role(Role role);
}
