package th.go.excise.ims.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0202JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0202Vo;

@Service
public class Oa0202Service {

	@Autowired
	private Oa0202JdbcRepository oa0202JdbcRep;

	public List<Oa0202Vo> findAll(String offCode, int addDate) {
		String strSeg = offCode.substring(0, 2);
		String strAra = offCode.substring(2, 4);
		String officeCon = "";
		if ("00".equals(strAra) && "00".equals(strSeg)) {
			// Main
			officeCon = "";
		} else {
			if ("00".equals(strAra) && !"00".equals(strSeg)) {
				// Sector
				officeCon = strSeg;
			} else if (!"00".equals(strAra) && "00".equals(strSeg)) {
				// Area
				officeCon = strAra;
			} else {
				// Self
				officeCon = strSeg;
			}
		}
		return oa0202JdbcRep.findAll(officeCon, addDate);
	}

}
