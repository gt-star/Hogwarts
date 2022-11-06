package ru.hogwarts.school.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.awt.print.Pageable;
import java.util.Collection;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {
    Avatar findByStudentId(Long id);
    Collection<Avatar> getAvatar(PageRequest pageRequest);
}
