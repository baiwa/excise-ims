import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { IaService } from 'services/ia.service';
import { ResultAnalysisForm } from 'projects/pages/result-analysis/result-analysis-form.model';
import { ResultAnalysisSerivce } from 'projects/pages/result-analysis/result-analysis.service';

declare var $: any;

@Component({
    selector: 'result-analysis',
    templateUrl: 'result-analysis.component.html',
    styles: [`
        
    `],
    providers: [ResultAnalysisSerivce]
})
export class ResultAnalysisPage implements OnInit {

    private category: String;
    private coordinate: String;
    form: ResultAnalysisForm = new ResultAnalysisForm();
    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private modalService: IaService,
        private resultAnalysisSerivce: ResultAnalysisSerivce
    ) {

    }

    ngOnInit() {
        
        this.setDataService();
    }

    ngAfterViewInit() {
        this.initDatatable1();
        this.initDatatable2();
        this.initDatatable3();
        this.initDatatable4();
        this.initDatatable5();
    }

    setDataService() {

        let model = this.modalService.getData();
        if(model == null){
            this.router.navigate(['/analysis']);
            return false;
        }
        console.log(model);

        this.resultAnalysisSerivce.findDataFromExciseId(model.exciseId).then(res => {

            console.log(res);
            //set data in service
            this.form.exciseId = model.exciseId;
            this.form.userNumber = res.analysNumber;
            this.form.dateFrom = model.dateFrom;
            this.form.dateTo = model.dateTo;
            this.form.entrepreneur = res.companyName; // query
            this.form.type = model.type;
            this.form.factory = res.factoryName; // query
            this.form.coordinates = model.coordinates;
            this.form.address = res.factoryAddress; //query
            this.form.analysisBy = res.createdBy; //query
            this.form.sector = res.exciseOwnerArea1 //query
        });

    }
    initDatatable1=()=> {
        this.resultAnalysisSerivce.initDatatable1();
    }

    initDatatable2=()=> {
        this.resultAnalysisSerivce.initDatatable2();
    }

    initDatatable3() {
        this.resultAnalysisSerivce.initDatatable3();        
    }

    initDatatable4() {
        this.resultAnalysisSerivce.initDatatable4();  
    }

    initDatatable5() {
        this.resultAnalysisSerivce.initDatatable5();
    }

    goToOpe03() {
        this.router.navigate(['/select-form', this.category, this.coordinate]);
    }
}