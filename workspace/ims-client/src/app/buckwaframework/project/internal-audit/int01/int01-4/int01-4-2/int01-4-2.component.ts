import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";
import { Int065Service } from "projects/internal-audit/int06/int06-5/int06-5.service";
import { AjaxService, MessageBarService } from "./../../../../../common/services";
import { Router } from "@angular/router";

declare var $: any;
@Component({
  selector: "app-int01-4-2",
  templateUrl: "./int01-4-2.component.html",
  styleUrls: ["./int01-4-2.component.css"]
})
export class Int0142Component implements OnInit {
  sectorList: any[];
  areaList: any;
  compareList:any[] = [];
  isLoading:boolean = false;
  isSearch:boolean = false;

  constructor(
    private authService: AuthService,
    private ajax: AjaxService,
    private router: Router,
    private messageBar : MessageBarService,
    private int065Service: Int065Service) {}

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01420');
    // $(".ui.dropdown").dropdown();
    // $(".ui.dropdown.ai").css("width", "100%");

    this.sector();
 
  }

  ngAfterViewInit() {
    //$("#selectZone").dropdown();
    //$("#selectArea").dropdown();
  }

  sector = () => {
    this.int065Service.sector().then((res) => {
      this.sectorList = res;
    });
  };

  area = (e) => {
    // $("#area").dropdown('restore defaults');
    // $("#branch").dropdown('restore defaults');
    this.areaList = null;
      this.int065Service.area(e.target.value).then((res) => {
        this.areaList = res;
      });
    
  }

  public compaireData(){
      let url = "ia/int014/compair"
      let prams = {

      };

      this.isLoading = true;
      this.isSearch = true;
      
      this.ajax.post(url,prams, (res)=>{
        this.isLoading = false;

        let json = res.json();
        console.log(json);
        if(json.status == "0"){
          console.log("ทำรายการสำเร็จ");
          // this.router.navigate(['/int01/4/3',{ uploadid : '1' }]);
          this.compareList = json.data.datas;
        }else{
          this.messageBar.alert("ไม่สามารถทำรายการได้.");
        }
      },
      (error)=>{
          // console.error("Error");
          this.messageBar.error("ไม่สามารถทำรายการได้");
          this.isLoading = false;
      });
  } 

}
