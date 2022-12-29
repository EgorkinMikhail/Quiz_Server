package org.example.grpc.service.theme;

import com.example.grpc.Theme;
import com.example.grpc.ThemeId;
import com.example.grpc.ThemeServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.logic.theme.ThemeLogic;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ThemeServiceImpl extends ThemeServiceGrpc.ThemeServiceImplBase {
    private final ThemeLogic themeLogic;

    @Override
    public void createTheme(Theme request, StreamObserver<ThemeId> responseObserver) {
        responseObserver.onNext(themeLogic.createTheme(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getTheme(ThemeId request, StreamObserver<Theme> responseObserver) {
        responseObserver.onNext(themeLogic.getTheme(request));
        responseObserver.onCompleted();
    }
}
