import { Component, OnInit } from "@angular/core";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { AuthService } from "services/auth.service";
import { Http } from "@angular/http";

declare var $: any;
@Component({
  selector: "app-int01-4-4",
  templateUrl: "./int01-4-4.component.html",
  styleUrls: ["./int01-4-4.component.css"]
})
export class Int0144Component implements OnInit {


  listConfig:ConfigMapping[] = [
    {
      accountDes : "รายได้ภาษีสุรา",
      accountNo : "4102020103",
      configId : 0,
      taxCode : "203010",
      taxDes : "ภาษีสุรา"
    }
  ];

  constructor(private messageBarService: MessageBarService) {}

  ngOnInit() {
  
  }

  ngAfterViewInit() {

   
  }

  
}

interface ConfigMapping{
    accountNo:string,
    accountDes:string,
    taxCode:string,
    taxDes:string,
    configId:number
}
