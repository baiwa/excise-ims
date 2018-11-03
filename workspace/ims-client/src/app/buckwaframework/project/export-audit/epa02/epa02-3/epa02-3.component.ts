import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-epa02-3',
  templateUrl: './epa02-3.component.html'
})
export class Epa023Component implements OnInit {

  private hdrId: string;
  private dtlId: string;

  leftformVo: INV_HDR;
  rightformVo: INV_HDR;

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-02300');

    // hdrId=1;dtlId=1

    this.route.paramMap.subscribe(v => {
      this.hdrId = v.get("hdrId");
      this.dtlId = v.get("dtlId");
    });

    this.leftformVo = {
      checkingResult: "N",
      checkPointDest: "",
      dateOut: "",
      exciseDest: "",
      exciseSrc: "",
      exportName: "",
      goodsNum: "",
      invType: "",
      productName: "",
      refNo: "",
      remark: "",
      route: "",
      transportName: ""
    };

    this.rightformVo = {
      checkingResult: "N",
      checkPointDest: "",
      dateOut: "",
      exciseDest: "",
      exciseSrc: "",
      exportName: "",
      goodsNum: "",
      invType: "",
      productName: "",
      refNo: "",
      remark: "",
      route: "",
      transportName: ""
    }

    this.ajax.post("epa/epa021/getInvDetail", { hdrId: this.hdrId, dtlId: this.dtlId }, (res) => {
      let data = res.json();
      console.log(data);

      this.leftformVo.exportName = data.hdrVo.exportName;
      this.leftformVo.exciseSrc = data.hdrVo.checkPointDest;
      this.leftformVo.checkPointDest = data.hdrVo.checkPointDest;
      this.leftformVo.exciseDest = data.hdrVo.checkPointDest;
      this.leftformVo.dateOut = data.hdrVo.dateOutDisplay;

      this.leftformVo.productName = data.dtlVo.productName;
      this.leftformVo.goodsNum = data.dtlVo.goodsNum;
      this.leftformVo.transportName = data.hdrVo.transportName;
      this.leftformVo.route = data.hdrVo.route;


      this.rightformVo = Object.assign({}, this.leftformVo);


    });



  }

  ngAfterViewInit(): void {
  }


  onClickSave(){
     let p = {
      hdrId: this.hdrId, dtlId: this.dtlId,

      "leftformVo" : this.leftformVo,
      "rightformVo" : this.rightformVo

     };
  }


}


interface INV_HDR {
  exportName: string,
  exciseSrc: string,
  checkPointDest: string,
  exciseDest: string,
  dateOut: string,
  productName: string,
  goodsNum: string,
  transportName: string,
  route: string,
  checkingResult: string,
  remark: string,
  refNo: string,
  invType: string,
}

// rightformVo
