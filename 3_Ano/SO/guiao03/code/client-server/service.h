namespace service {

struct ServiceRequest {
    char* data;
    int size;
};

struct ServiceResponse {
    int size;
    char* data;
};

void create();
void destroy();

void callService(ServiceRequest& req, ServiceResponse& res);
void processService();
}  // namespace service