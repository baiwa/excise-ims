import { NgModule } from '@angular/core';
import { NumberDirective } from './number.directive';
import { CommonModule } from '@angular/common';


@NgModule({
    imports: [CommonModule],
    declarations: [
        NumberDirective
    ],
    exports: [
        NumberDirective
    ]
})
export class DirectivesModule { }
