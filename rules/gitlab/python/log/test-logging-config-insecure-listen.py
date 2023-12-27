# License: MIT (c) GitLab Inc.
# source: https://github.com/PyCQA/bandit/blob/main/examples/logging_config_insecure_listen.py (incl modifications)

import logging
from logging import config as aliased_cfg

logging.config.listen(9999) # FAIL
aliased_cfg.listen(8888) # FAIL
