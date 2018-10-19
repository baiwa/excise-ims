import { Injectable } from '@angular/core';
import { SummaryModel } from 'projects/tax-audit/trader-selection/analyst-basic-data-trader/summaryFooter.model';

@Injectable()
export class AnalystBasicDataTraderService {
    
    summary : SummaryModel = new SummaryModel();
    constructor(){

    }
}