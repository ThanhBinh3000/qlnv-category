package com.tcdt.qlnvcategory.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tcdt.qlnvcategory.request.object.catalog.QlnvDmDonviReq;
import com.tcdt.qlnvcategory.request.search.catalog.QlnvDmDonviSearchReq;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.tcdt.qlnvcategory.repository.catalog.QlnvDmDonviRepository;
import com.tcdt.qlnvcategory.table.catalog.QlnvDmDonvi;

import javax.servlet.http.HttpServletRequest;

@Service
@CacheConfig(cacheNames = "dviCache")
public class DonViService extends BaseService  {
	@Autowired
	private QlnvDmDonviRepository qlnvDmDonviRepository;

	public QlnvDmDonvi getDonViById(String id) throws Exception {
		Optional<QlnvDmDonvi> qOptional = qlnvDmDonviRepository.findById(Long.parseLong(id));
		if (!qOptional.isPresent()){
			Optional<QlnvDmDonvi> qOptionalMaDvi = qlnvDmDonviRepository.findByMaDvi(id);
			if(!qOptionalMaDvi.isPresent()){
				throw new Exception("Đơn vị không tồn tại.");
			}
			return qOptionalMaDvi.get();
		}
		return qOptional.get();
	}

	public QlnvDmDonvi save(QlnvDmDonviReq objReq, HttpServletRequest req) throws Exception{
//		if (qlnvDmDonviRepository.findByMaDvi(objReq.getMaDvi()) != null){
//			throw new UnsupportedOperationException("Mã đơn vị đã tồn tại");
//		}
		QlnvDmDonvi dataMap = new ModelMapper().map(objReq, QlnvDmDonvi.class);
		dataMap.setNguoiTao(getUserName(req));
		dataMap.setNgayTao(new Date());
		QlnvDmDonvi createCheck = qlnvDmDonviRepository.save(dataMap);
		return createCheck;
	}

	public QlnvDmDonvi update(QlnvDmDonviReq objReq, HttpServletRequest req) throws Exception {
		Optional<QlnvDmDonvi> qOptional = qlnvDmDonviRepository.findById(objReq.getId());

		if (!qOptional.isPresent())
			throw new UnsupportedOperationException("Mã đơn vị không tồn tại");

		QlnvDmDonvi dataDTB = qOptional.get();
		QlnvDmDonvi dataMap = new ModelMapper().map(objReq, QlnvDmDonvi.class);

		updateObjectToObject(dataDTB, dataMap);
		dataDTB.setNguoiSua(getUserName(req));
		dataDTB.setNgaySua(new Date());
		QlnvDmDonvi createCheck = qlnvDmDonviRepository.save(dataDTB);
		return createCheck;
	}

	public void delete(long ids){
		Optional<QlnvDmDonvi> qOptional = qlnvDmDonviRepository.findById(ids);
		if (!qOptional.isPresent()){
			throw new UnsupportedOperationException("Không tồn tại bản ghi");
		}
		if(qOptional.get().getChildren() != null && !qOptional.get().getChildren().isEmpty()){
			throw new UnsupportedOperationException("Không thể xóa đơn vị có đơn vị con");
		}
		qlnvDmDonviRepository.delete(qOptional.get());
	}

	public List<QlnvDmDonvi> findByTrangThai(String status){
		List<QlnvDmDonvi> dataMap = qlnvDmDonviRepository.findByTrangThai(status);
		dataMap.forEach( item -> item.setChildren(null));
		return dataMap;
	}

	public List<QlnvDmDonvi> getAll(QlnvDmDonviSearchReq objReq){
		List<QlnvDmDonvi> dataMap = qlnvDmDonviRepository.selectAll(objReq.getCapDvi(),objReq.getMaDviCha(),objReq.getTrangThai());
		dataMap.forEach( item -> item.setChildren(null));
		return dataMap;
	}

	public List<QlnvDmDonvi> getAllByLevel(String capDvi, String trangThai){
		List<QlnvDmDonvi> dataMap =  qlnvDmDonviRepository.selectAll(capDvi,null,trangThai);
		dataMap.forEach( item -> item.setChildren(null));
		return dataMap;
	}
	public List<QlnvDmDonvi> getAllTree(QlnvDmDonviSearchReq objReq){
		return qlnvDmDonviRepository.selectAllTree(objReq.getMaDviCha(),objReq.getTrangThai());
	}
}
