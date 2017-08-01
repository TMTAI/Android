
package conf;


import controllers.LoginLogoutController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;
import service.DienKeService;
import service.HoaDonService;
import service.KhachHangService;
import service.UserService;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
        router.GET().route("/").with(ApplicationController::index);
        router.GET().route("/login").with(LoginLogoutController::login);
        router.GET().route("/logout").with(LoginLogoutController::logout);

        router.GET().route("/getKhuVuc").with(ApplicationController::getKhuVuc);
        router.GET().route("/getThanhPho").with(ApplicationController::getThanhPho);
        router.GET().route("/getAllKhachHang").with(KhachHangService::getAllKhachHang);
        router.GET().route("/getListKhachHang").with(KhachHangService::getKhachHangStatistic);
        router.POST().route("/addKhachHang").with(KhachHangService::addKhachHang);
        router.POST().route("/deleteKhachHang").with(KhachHangService::deleteKhachHang);


        router.GET().route("/getAllUsers").with(UserService::getAllUsers);
        router.GET().route("/getUser").with(UserService::getUser);

        router.GET().route("/getDienKe").with(DienKeService::getDienKe);
        router.POST().route("/updateChiSoDienKe").with(DienKeService::updateDienKe);
        router.POST().route("/addDienKe").with(DienKeService::addDienKe);

        router.GET().route("/getHoaDon").with(HoaDonService::getHoaDon);

        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
