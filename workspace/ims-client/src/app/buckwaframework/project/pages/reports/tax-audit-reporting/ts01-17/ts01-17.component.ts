import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MessageBarService, AjaxService } from '../../../../../common/services';
import { ActivatedRoute } from '@angular/router';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-ts01-17',
  templateUrl: './ts01-17.component.html',
  styleUrls: ['./ts01-17.component.css']
})
export class Ts0117Component implements OnInit {


  @Output() discard = new EventEmitter<any>();
  numbers: number[];
  obj: Ts0114;
  add: number;

 
  constructor(
    private messageBarService: MessageBarService,
    private route: ActivatedRoute,
    private ajax: AjaxService) {
    this.add = 0;
    this.numbers = [1, 2, 3];
    this.obj = new Ts0114();

    this.obj.exciseId = this.route.snapshot.queryParams["exciseId"];
  }

  ngOnInit() {
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 5) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal(
        "ไม่สามารถทำรายการได้",
        "เกิดข้อผิดพลาด"
      );
    }
  };

  onDelField = index => {
    this.obj["analys" + (index + 1)] = "";
    this.numbers.splice(index, 1);
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


  onSubmit = e => {
    e.preventDefault();
    const url = "report/pdf/ts/mis_t_s_01_17_1";
    if (this.obj.agreeBox == "factory") {
      this.obj.factory1 = true;
      this.obj.brothel1 = false;
      this.obj.importer1 = false;
    } else if (this.obj.agreeBox == " brothel1") {
      this.obj.factory1 = false;
      this.obj. brothel1 = true;
      this.obj.importer1 = false;
    } else if (this.obj.agreeBox == "importer1") {
      this.obj.factory1 = false;
      this.obj.importer1 = false;
      this.obj.importer1 = true;
    }
    this.ajax.post(url, `'${JSON.stringify(this.obj).toString()}'`, res => {
      if (res.status == 200 && res.statusText == "OK") {


        window.open("/ims-webapp/api/report/pdf/mis_t_s_01_17_1/file");
      }
    });
  };
}

class Ts0114 {
  exciseId: string;
  logo1: string = "logo1.jpg";

  myName1: string;
  date1: string;
  monht1: string;
  buddhist1: string;
  postion1: string;
  Nameplace1: string;
  bookNumber1: string;
  exciseRegister1: string;
  checkInOn1: string;
  checktime1: string;
  dateCheck1: string;
  acditor1: string;



  agreeBox: string;
  factory1: boolean = false;
  brothel1: boolean = false;
  importer1: boolean = false;


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
	month1  : string;
	time: string;

	at2: string;
	at3: string;
	at1: string;
	at4: string;
	laws: string;
	faet: string;
	paytaxes: string;
	withindate: string;
	person: string;
	invitationCard: string;
	noet: string;
	department: string;	
	withindate1: string;
	paytaxes1: string;
	date2: string;
	month: string;
	buddhist2 : string;
	total1: string;
	study1: string;
	includes1: string;
	result1: string;
	according1: string;
	fromtne: string;
	exciseTax1: string;
	fine: string;
	exteamoney: string;
	gorernment: string;


}
