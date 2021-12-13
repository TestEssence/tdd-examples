@pytest.fixture(scope="module")
def stream_id():
    return "31e45c5c-7073-4941-bd64-e2f43238cad8"


@pytest.fixture(scope="module")
def device_log():
    return BobStreamLog(
        file_name=f"./output/{stream_id}.device.grpc.json",
        log_type=LogType.device,
    )


@pytest.fixture(scope="module")
def grpc_client_log():
    return BobStreamLog(
        file_name=f"./output/{stream_id}.client.grpc.json",
        log_type=LogType.client_grpc,
    )


@pytest.fixture(scope="module")
def http_client_log():
    return BobStreamLog(
        file_name=f"./output/{stream_id}.client.http.json",
        log_type=LogType.client_http,
    )


def test_grpc_client_log_matches_device(device_log, grpc_client_log):
    assert device_log.ends_with(
        grpc_client_log, allowed_gap_in_seconds=3
    ), f"{client_type}: The number of missed chunks should not exceed 3 seconds"


def test_http_client_log_matches_device(device_log, http_client_log):
    assert device_log.ends_with(
        http_client_log, allowed_gap_in_seconds=3
    ), f"{client_type}: The number of missed chunks should not exceed 3 seconds"


def test_client_logs_equals(grpc_client_log, http_client_log):
    intersection = grpc_client_log & http_client_log
    assert intersection is not None
    assert grpc_client_log == http_client_log
