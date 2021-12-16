@pytest.fixture(scope="module")
def stream_id():
    return "31e45c5c-7073-4941-bd64-e2f43238cad8"


@pytest.fixture(scope="module")
def device_log():
    return StreamLog(
        file_name=f"./output/{stream_id}.device.grpc.log",
        log_type=LogType.device,
    )


@pytest.fixture(scope="module")
def client_log():
    return StreamLog(
        file_name=f"./output/{stream_id}.client.http.log",
        log_type=LogType.client_grpc,
    )


@pytest.mark.validation
def test_http_client_log_matches_device(device_log, client_log):
    assert device_log == client_log, "stream content should be the same"
