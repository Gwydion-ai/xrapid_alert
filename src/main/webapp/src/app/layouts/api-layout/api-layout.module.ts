import {NgModule} from "@angular/core";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {CommonModule} from "@angular/common";

import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {SupportLayoutRoutes} from "./api-layout.routing";
import {ApiComponent} from "../../pages/support-us/api.component";
import {NgxSpinnerModule} from "ngx-spinner";

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(SupportLayoutRoutes),
        HttpClientModule,
        NgbModule,
        NgxSpinnerModule
    ],
    exports: [
        ApiComponent
    ],
    declarations: [
        ApiComponent
    ]
})
export class ApiLayoutModule {}
