package com.tcdt.qlnvcategory.secification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmTchuanHdrSearchReq;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmTchuanHdr;

public class QlnvDmTchuanHdrSpecification {
	public static Specification<QlnvDmTchuanHdr> buildSearchQuery(final QlnvDmTchuanHdrSearchReq objReq) {
		return new Specification<QlnvDmTchuanHdr>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3571167956165654062L;

			@Override
			public Predicate toPredicate(Root<QlnvDmTchuanHdr> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (ObjectUtils.isEmpty(objReq))
					return predicate;

				String maHang = objReq.getMaHang();
				String tenQchuan = objReq.getTenQchuan();
				String trangThai = objReq.getTrangThai();

				if (StringUtils.isNotEmpty(maHang))
					predicate.getExpressions()
							.add(builder.like(builder.lower(root.get("maHang")), "%" + maHang.toLowerCase() + "%"));

				if (StringUtils.isNotEmpty(tenQchuan))
					predicate.getExpressions().add(
							builder.like(builder.lower(root.get("tenQchuan")), "%" + tenQchuan.toLowerCase() + "%"));

				if (StringUtils.isNotBlank(trangThai))
					predicate.getExpressions().add(builder.and(builder.equal(root.get("trangThai"), trangThai)));

				return predicate;
			}
		};
	}
}
