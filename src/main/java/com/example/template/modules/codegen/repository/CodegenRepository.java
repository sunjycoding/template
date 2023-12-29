package com.example.template.modules.codegen.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author created by sunjy on 12/28/23
 */
@Repository
public class CodegenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Long getTableTotalElements() {
        String countQuery = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE()";
        return ((Number) entityManager.createNativeQuery(countQuery).getSingleResult()).longValue();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findAllTable(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE table_schema = DATABASE() " +
                "limit ?1, ?2";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, offset);
        query.setParameter(2, pageSize);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findAllTable(int pageNumber, int pageSize, String tableName) {
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE table_schema = DATABASE() AND " +
                "TABLE_NAME LIKE ?1 " +
                "limit ?2, ?3";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, "%" + tableName + "%");
        query.setParameter(2, offset);
        query.setParameter(3, pageSize);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findAllTable() {
        String sql = "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE table_schema = DATABASE()";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findAllColumn(String tableName) {
        String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT, IS_NULLABLE, COLUMN_KEY " +
                "FROM information_schema.COLUMNS " +
                "WHERE TABLE_SCHEMA = DATABASE() " +
                "AND TABLE_NAME = ?1 " +
                "ORDER BY ORDINAL_POSITION;";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, tableName);
        return query.getResultList();
    }

}
