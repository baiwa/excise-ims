import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AjaxService } from "../../../../../common/services";
import { ActivatedRoute } from "@angular/router";
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-ts01-03',
  templateUrl: './ts01-03.component.html',
  styleUrls: ['./ts01-03.component.css']
})
export class Ts0103Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  obj: Ts0103;



  constructor(
    private route: ActivatedRoute,
    private ajax: AjaxService
  ) {
    this.obj = new Ts0103();
    this.obj.exciseId = this.route.snapshot.queryParams["exciseId"];
  }

  ngOnInit() {
    $('#begin_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter()
    });
    $('#end_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter()
    });
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };




  optionAddress = () => {
    const optionURL = "excise/detail/objectAddressByExciseId";
    this.ajax.post(optionURL, {
      exciseId: this.obj.exciseId
    }, res => {
      console.log(res.json());
      var dat = res.json();
      this.obj.homeNumber = dat.homeNumber;
      this.obj.byWay = dat.byWay;
      this.obj.street = dat.street;
      this.obj.tambol = dat.tambol;
      this.obj.district = dat.district;
      this.obj.province = dat.province;
      this.obj.zipCode = dat.zipCode;
    });
  };

  isAddress = () => {
    if (this.obj.addressBox !== true) {
      this.obj.homeNumberC = this.obj.homeNumber;
      this.obj.byWayC = this.obj.byWay;
      this.obj.streetC = this.obj.street;
      this.obj.tambolC = this.obj.tambol;
      this.obj.districtC = this.obj.district;
      this.obj.provinceC = this.obj.province;
      this.obj.zipCodeC = this.obj.zipCode;
    } else {
      this.obj.homeNumberC = "";
      this.obj.byWayC = "";
      this.obj.streetC = "";
      this.obj.tambolC = "";
      this.obj.districtC = "";
      this.obj.provinceC = "";
      this.obj.zipCodeC = "";
    }
  }


  onSubmit = e => {
    e.preventDefault();
    const url = "report/pdf/ts/mis_t_s_01_03";
    if (this.obj.agreeBox == "factory") {
      this.obj.factory = true;
      this.obj.service = false;
      this.obj.company = false;
    } else if (this.obj.agreeBox == "service") {
      this.obj.factory = false;
      this.obj.service = true;
      this.obj.company = false;
    } else if (this.obj.agreeBox == "company") {
      this.obj.factory = false;
      this.obj.service = false;
      this.obj.company = true;
    }
    this.ajax.post(url, `'${JSON.stringify(this.obj).toString()}'`, res => {
      if (res.status == 200 && res.statusText == "OK") {
        window.open("/ims-webapp/api/report/pdf/mis_t_s_01_03/file");
      }
    });
  };

}
class Ts0103 {
  logo: string = "logo1.jpg";
  [x: string]: any;
  office: string;
  department: string;

  date1: string;

  agreeBox: string;
  factory: boolean;
  service: boolean;
  company: boolean;

  name: string;
  exciseId: string;

  homeNumber: string;
  moo: string;
  building: string;
  level: string;
  byWay: string;
  street: string;
  tambol: string;
  district: string;
  province: string;
  zipCode: string;

  addressBox: boolean;
  homeNumberC: string;
  mooC: string;
  buildingC: string;
  levelC: string;
  byWayC: string;
  streetC: string;
  tambolC: string;
  districtC: string;
  provinceC: string;
  zipCodeC: string;

  with: string;
  article: string;
  group: string;
  location: string;
  date2: string;
  document: string;

  signature: string;
  phoneNumber: string;

}