package th.go.excise.ims.ta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaxOperatorService {

    private static final Logger logger = LoggerFactory.getLogger(TaxOperatorService.class);

    @Autowired
    private DraftWorksheetService draftWorksheetService;

    //TODO Tax  Operator 2
    // TODO DRAFT
    public TaxOperatorVo getPreviewData(TaxOperatorFormVo formVo) {
        return draftWorksheetService.getPreviewData(formVo);
    }


}
