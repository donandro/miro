package com.miro.task.store.db;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Integer> {
	
	@Query("select w from Widget w "
			+ "where zIndex = (:zIndex)")
	public Optional<Widget> findByZIndex(@Param("zIndex") int zIndex);
	
	@Modifying
	@Query("update Widget w set w.zIndex = w.zIndex + 1, w.modifyDate = (:modifyDate) "
		      + "where w.zIndex >= (:zIndex)")
	public int moveWidgets(@Param("zIndex") int zIndex, @Param("modifyDate") Date modifyDate);
	
	public default int moveWidgets(int zIndex) {
		return moveWidgets(zIndex, new Date());
	}
	
	@Query("select MAX(w.zIndex) from Widget w")
	public Integer findMaxZIndex();
	
	@Query("select w from Widget w ORDER BY w.id desc")
	public List<Widget> findAllOrderByIdAsc(Pageable pageble);
}