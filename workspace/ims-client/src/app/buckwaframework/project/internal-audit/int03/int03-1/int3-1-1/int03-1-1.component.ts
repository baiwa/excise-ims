import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";

declare var $: any;
@Component({
  selector: "int03-1-1",
  templateUrl: "./int03-1-1.component.html",
  styleUrls: ["./int03-1-1.component.css"]
})
export class Int0311Component implements OnInit {
  selectZone: any[];
  selectArea: any[];

  constructor(
    private authService: AuthService) {}

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-03110');
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.selectZone = [
      { value: "สำนักงานสรรพสามิตภาคที่ 1" },
      { value: "สำนักงานสรรพสามิตภาคที่ 2" },
      { value: "สำนักงานสรรพสามิตภาคที่ 3" },
      { value: "สำนักงานสรรพสามิตภาคที่ 4" },
      { value: "สำนักงานสรรพสามิตภาคที่ 5" },
      { value: "สำนักงานสรรพสามิตภาคที่ 6" },
      { value: "สำนักงานสรรพสามิตภาคที่ 7" }
    ];

    this.selectArea = [
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรสาคร" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรสงคราม" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรปราการ" }
    ];
  }

  ngAfterViewInit() {
    //$("#selectZone").dropdown();
    //$("#selectArea").dropdown();
  }
}
