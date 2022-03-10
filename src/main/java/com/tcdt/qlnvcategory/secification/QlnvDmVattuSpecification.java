package com.tcdt.qlnvcategory.secification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmVattuSearchReq;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmVattu;

public class QlnvDmVattuSpecification {
	public static Specification<QlnvDmVattu> buildSearchQuery(final QlnvDmVattuSearchReq objReq) {
		return new Specification<QlnvDmVattu>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3571167956165654062L;

			@Override
			public Predicate toPredicate(Root<QlnvDmVattu> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate predicate = builder.conjunction();
				if (ObjectUtils.isEmpty(objReq))
					return predicate;

				String ma = objReq.getMa();
				String ten = objReq.getTen();
				String trangThai = objReq.getTrangThai();

				root.fetch("children", JoinType.LEFT);

				if (StringUtils.isNotEmpty(ma))
					predicate.getExpressions()
							.add(builder.like(builder.lower(root.get("ma")), "%" + ma.toLowerCase() + "%"));

				if (StringUtils.isNotEmpty(ten))
					predicate.getExpressions()
							.add(builder.like(builder.lower(root.get("ten")), "%" + ten.toLowerCase() + "%"));

				if (StringUtils.isNotBlank(trangThai))
					predicate.getExpressions().add(builder.and(builder.equal(root.get("trangThai"), trangThai)));

				return predicate;
			}
		};
	}
}
