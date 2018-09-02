package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.vo.Int02m2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int02m2VoDetail;

@Service
public class Int02m2Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<Int02m2Vo> findByCreteria() { // Mock
		List<Int02m2Vo> result = new ArrayList<>();
		List<Int02m2VoDetail> details;
		Int02m2Vo main ;
		Int02m2VoDetail detail;
		for(int i=1; i<=3; i++) {
			int k = 1, h = 1;
			main = new Int02m2Vo();
			main.setTitle("การเงิน " + i);
			main.setContent("-");
			main.setConclusion(true);
			details = new ArrayList<>();
			for(int j=1; j<6; j++) {
				detail = new Int02m2VoDetail();
				if (j == 1) {
					detail.setHeaderId(Long.valueOf(j));
					detail.setOrder("" + j);
					detail.setContent("หลัก " + j);
					k = 1;
					h = 1;
				} else {
					detail.setDetailId(Long.valueOf(k));
					detail.setOrder(h + "." + k++);
					detail.setContent("ย่อย " + h + "." + k);
				}
				details.add(detail);
			}
			main.setDetail(details);
			result.add(main);
		}
		return result;
	}
	
}
