import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter ,stringToDate} from "../../../../../common/helper/datepicker";
import { AjaxService, MessageBarService, AuthService } from "../../../../../common/services";
import { TravelCostHeader } from "../../../../../common/models";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
import { IaService } from 'app/buckwaframework/common/services/ia.service';
import { BreadCrumb } from 'models/index';
import { Observable } from "rxjs/Observable";
import { ComboBox } from "models/combobox";
import { Utils } from "helpers/utils";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Int06119Service } from "projects/internal-audit/int06/int06-11/int06-11-9/int06-11-9.service";


declare var $: any;
@Component({
  selector: "app-int06-11-9",
  templateUrl: "./int06-11-9.component.html",
  styleUrls: ["./int06-11-9.component.css"],
  providers: [Int06119Service]
})
export class Int06119Component implements OnInit {
  searchForm: FormGroup;
  breadcrumb: BreadCrumb[];
  submitted: boolean = false;
  withdrawRequestList: Observable<ComboBox>;
  dataApprove:any=[];

  constructor(
    private selfService: Int06119Service,
    private fb: FormBuilder,
    private msg: MessageBarService,
    private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private authService: AuthService,
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบหลักฐานการขอเบิก และอนุมัติการขอเบิก", route: "#" }
    ];

    this.withdrawRequestList = selfService.dropdown("WITHDRAW_REQUEST", null);
  }

  setVariable() {
    this.searchForm = this.fb.group({
      withdrawRequest: ["", Validators.required],
      createdBy: [""]
    });
  }

  ngOnInit() {  
    this.authService.reRenderVersionProgram("INT-06119").then(obj => {
      this.searchForm.patchValue({
        createdBy: obj.fullName,
        // position: obj.title,
        // affiliation: "-"
      });
    });
    
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    this.setVariable();
  }

  ngAfterViewInit(): void {
    this.selfService.Datatable();
  }

  handleSearch(e) {
    e.preventDefault();
    this.selfService.search(this.searchForm.value);
    
  }

  callBackData(){
    this.dataApprove = this.selfService.dataApprove; 
    this.dataApprove.amount = Utils.moneyFormatDecimal(this.selfService.dataApprove.amount);
    this.dataApprove.amountPay = Utils.moneyFormatDecimal(this.selfService.dataApprove.amountPay);
    
  }

  editData=()=>{
    console.log("Edit");
    $('#modalEdit').modal('hide');
    const URL = "ia/int061109/edit";
    this.ajax.post(URL, { 
        id:$("#id").val(),
        billLading:$("#billLading").val()
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.msg.successModal(commonMessage.msg.messageTh);
    } else {
      this.msg.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    $('#dataTable').DataTable().ajax.reload();
    });
  }

  addData=()=>{
    console.log("Add");
    $('#modalAdd').modal('hide');
    const URL = "ia/int061109/add";
    this.ajax.post(URL, { 
        id:$("#id").val(),
        billPay:$("#billPay").val(),
        amountPay:$("#amountPay").val()
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.msg.successModal(commonMessage.msg.messageTh);
    } else {
      this.msg.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    $('#dataTable').DataTable().ajax.reload();
    });
  }
  approve=(status)=>{
    console.log("Approve");
    const URL = "ia/int061109/approve";
    this.ajax.post(URL, { 
        id:this.dataApprove.id,
        status:status
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.msg.successModal(commonMessage.msg.messageTh);
    } else {
      this.msg.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    $('#dataTable').DataTable().ajax.reload();
    });
  }

}
