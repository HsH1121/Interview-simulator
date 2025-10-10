// 간단 공통 유틸
(function () {
  function getMeta(name) {
    const el = document.querySelector(`meta[name="${name}"]`);
    return el ? el.content : '';
  }

  const CSRF = {
    header: getMeta('csrf-header'),  // 예: X-CSRF-TOKEN 또는 X-XSRF-TOKEN
    token:  getMeta('csrf-token'),   // 실제 토큰 값
    param:  getMeta('csrf-param')    // 폼 파라미터명 (전통 submit 시 사용)
  };

  async function postJson(url, payload) {
    const headers = { 'Content-Type': 'application/json' };
    if (CSRF.header && CSRF.token) headers[CSRF.header] = CSRF.token;

    const res = await fetch(url, {
      method: 'POST',
      headers,
      body: JSON.stringify(payload),
      credentials: 'same-origin' // 쿠키 세션 사용하는 경우
    });

    const text = await res.text();
    let data = null;
    try { data = JSON.parse(text); } catch { /* 그대로 text 사용 */ }

    return { ok: res.ok, status: res.status, data: data ?? text };
  }

  function showMsg(ok, data, msgEl, successPrefix) {
    msgEl.className = 'msg ' + (ok ? 'ok' : 'err');

    if (!ok) {
      const message = data && data.message ? data.message : `오류: ${typeof data === 'string' ? data : ''}`;
      msgEl.textContent = message;
      return;
    }

    // 성공: MemberResponse 형태 가정 {id, email, nickname, createdAt}
    msgEl.textContent =
      `${successPrefix}\n` +
      `ID: ${data.id}\n` +
      `Email: ${data.email}\n` +
      `Nickname: ${data.nickname}\n` +
      `CreatedAt: ${data.createdAt}`;
  }

  function initSignup(apiUrl) {
    const form = document.getElementById('signupForm');
    const msg  = document.getElementById('msg');
    if (!form) return;

    form.addEventListener('submit', async (e) => {
      e.preventDefault();
      msg.textContent = '';

      const payload = {
        email: form.email.value.trim(),
        password: form.password.value,
        nickname: form.nickname.value.trim()
      };

      try {
        const { ok, data } = await postJson(apiUrl, payload);
        showMsg(ok, data, msg, '가입 완료!');
        if (ok) form.reset();
      } catch (err) {
        msg.className = 'msg err';
        msg.textContent = '요청 실패. 네트워크를 확인해주세요.';
      }
    });
  }

  function initLogin(apiUrl) {
    const form = document.getElementById('loginForm');
    const msg  = document.getElementById('msg');
    if (!form) return;

    form.addEventListener('submit', async (e) => {
      e.preventDefault();
      msg.textContent = '';

      const payload = {
        email: form.email.value.trim(),
        password: form.password.value
      };

      try {
        const { ok, data } = await postJson(apiUrl, payload);
        showMsg(ok, data, msg, '로그인 성공!');
      } catch (err) {
        msg.className = 'msg err';
        msg.textContent = '요청 실패. 네트워크를 확인해주세요.';
      }
    });
  }

  // 전역 노출
  window.Auth = { initSignup, initLogin };
})();
